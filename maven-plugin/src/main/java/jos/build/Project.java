/*
 * Copyright (C) 2012 HipByte SPRL and contributors
 */
package jos.build;

import static jos.build.Application.sh;
import static jos.build.Application.system;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import jos.build.Application.Platform;
import jos.build.Configuration.BuildMode;
import jos.build.Configuration.Family;
import jos.build.Configuration.Retina;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class Project {

    /**
     * Build the simulator version.
     */
    public static void simulatorBuild() {
        Application.build(Platform.IPHONE_SIMULATOR);
    }

    /**
     * Build the device version.
     */
    public static void deviceBuild() {
        Application.build(Platform.IPHONE_OS);
        Application.codeSign(Platform.IPHONE_OS);
    }

    /**
     * Run the simulator.
     */
    public static void simulator() {
        simulatorBuild();
        final File app = Application.getConfig().getAppBundle(Platform.IPHONE_SIMULATOR);
        final float target;
        if (System.getenv().containsKey("target")) {
            target = Float.valueOf(System.getenv("target"));
        } else {
            target = Application.getConfig().getSdkVersion();
        }

        // Cleanup the simulator application sandbox, to avoid having old resource files there.
        if (System.getenv("clean") != null) {
            final File simApps = new File("~/Library/Application Support/iPhone Simulator/"+target+"/Applications");
            for (final File appBundle : FileUtils.listFiles(simApps, new String[] {"app"}, true)) {
                if (appBundle.getName().equals(app.getName())) {
                    FileUtils.deleteQuietly(appBundle);
                    break;
                }
            }
        }

        // Prepare the device family.
        final int familyInt;
        if (System.getenv().containsKey("device_family")) {
            familyInt = Family.valueOf(System.getenv("device_family").toLowerCase()).getFamilyInt();
        } else {
            familyInt = Application.getConfig().getDeviceFamilyInts()[0];
        }
        final Family family = Family.values()[familyInt];
        final Retina retina = System.getenv().containsKey("retina") ? Retina.FALSE : Retina.FALSE;

        // Configure the SimulateDevice variable (the only way to specify if we want to run in retina mode or not).
        final String simulateDevice = Application.getConfig().getDeviceFamilyString(family, target, retina);
        if (!sh("/usr/bin/defaults read com.apple.iphonesimulator \"SimulateDevice\"").trim().equals(simulateDevice)) {
            system("/usr/bin/killall \"iPhone Simulator\" >& /dev/null");
            system("/usr/bin/defaults write com.apple.iphonesimulator \"SimulateDevice\" \""+simulateDevice+"\"");
        }

        // Launch the simulator.
        final String xcode = Application.getConfig().getXcodeDir();
        String env = xcode.matches("^/Applications/") ? "DYLD_FRAMEWORK_PATH=\""+xcode+"/../Frameworks\":\""+xcode+"/../OtherFrameworks\"" : "";
        if (Application.getConfig().isSpecMode()) {
            env += " SIM_SPEC_MODE=1";
        }
        final File sim = new File(Application.getConfig().getBinDir(), "sim");
        final int debug = System.getenv().containsKey("debug") ? 1 : Application.getConfig().isSpecMode() ? 0 : 2;
        Application.info("Simulate", app);
        /*Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				if (System.out.istty()) {
					// Just in case the simulator launcher crashes and leaves the terminal without echo.
					system("stty echo");
				}
			}
		}));*/
        sh(String.format("%s %s %d %d %f \"%s\" \"%s\"", env, sim, debug, familyInt, target, xcode, app));
    }

    /**
     * Create an .ipa archive.
     */
    public static void archive() {
        deviceBuild();
        Application.archive();
    }

    /**
     * Create an .ipa archive for distribution (AppStore).
     */
    public static void distributionArchive() {
        Application.getConfigWithoutSetup().setBuildMode(BuildMode.RELEASE);
        Application.getConfig().setDistributionMode(true);
        deviceBuild();
        Application.archive();
    }

    /**
     * Run the test suite on the simulator.
     */
    public static void simulatorTest() {
        Application.getConfig().setSpecMode(true);
        simulator();
    }

    /**
     * Run the test suite on the device.
     */
    public static void deviceTest() {
        Application.getConfig().setSpecMode(true);
        //System.getenv().put"debug", "1");
        device();
    }

    public static void device() {
        archive();
        Application.info("Deploy", Application.getConfig().getArchive());
        final String deviceId;
        if (System.getenv().containsKey("id")) {
            deviceId = System.getenv("id");
        } else {
            deviceId = Application.getConfig().getDeviceId();
        }
        if (!Application.getConfig().getProvisionedDevices().contains(deviceId)) {
            Application.fail("Device ID '"+deviceId+"' not provisioned in profile '"+Application.getConfig().getProvisioningProfile()+"'");
        }
        final String env = "XCODE_DIR=\""+Application.getConfig().getXcodeDir()+"\"";
        final File deploy = new File(Application.getConfig().getBinDir(), "deploy");
        final String flags = "";//"-d";
        sh(String.format("%s %s %s \"%s\" \"%s\"", env, deploy, flags, deviceId, Application.getConfig().getArchive()));
    }

    /**
     * Clear build objects.
     */
    public static void clean() {
        Application.info("Delete", Application.getConfig().getBuildDir());
        FileUtils.deleteQuietly(Application.getConfig().getBuildDir());
        for (final Vendor vendor : Application.getConfig().getVendorProjects()) {
            vendor.clean();
        }
        for (final File p : FileUtils.listFiles(Application.getConfig().getResourcesDir(), new String[] {"nib", "storyboardc", "momd"}, true)) {
            Application.info("Delete", p);
            FileUtils.deleteQuietly(p);
        }
    }

    /**
     * Show project config.
     */
    public static void config() {
//        final Map<String, String> map = Application.getConfig().variables();
//        final List<String> keys = Lists.newArrayList(map.keySet());
//        Collections.sort(keys);
//        for (final String key : keys) {
//            System.out.printf("%22s : %s", key, map.get(key));
//            System.out.println();
//        }
    }

    /**
     * Generate ctags.
     */
    public static void ctags() {
        final File tagsFile = new File("tags");
        final Configuration config = Application.getConfig();
        if (!tagsFile.exists() || config.getProjectFile().lastModified() > tagsFile.lastModified()) {
            //bs_files = config.bridgesupport_files + config.vendor_projects.map { |p| Dir.glob(File.join(p.path, '*.bridgesupport')) }.flatten
            //final File ctags = new File(config.bindir(), "ctags");
            //final File cfg = new File(new File(config.motiondir, "data"), "bridgesupport-ctags.cfg");
            //sh(ctags+" --options=\""+config+"\" "+bs_files.map { |x| "\"" + x + "\"" }.join(" ")}");
        }
    }

    /**
     * "Create a .a static library.
     */
    public static void staticLib() {
        final Map<String, String> opts = Maps.newHashMap();
        opts.put("static", "true");
        final String libs = StringUtils.join(Lists.transform(Lists.newArrayList(Platform.values()), new Function<Platform, String>() {
            public String apply(final Platform platform) {
                return "\"" + Application.build(platform, opts) + "\"";
            }
        }), " ");
        final File fatLib = new File(Application.getConfig().getBuildDir(), Application.getConfig().getName() + "-universal.a");
        Application.info("Create", fatLib);
        sh("/usr/bin/lipo -create "+libs+" -output \""+fatLib+"\"");
    }
}
