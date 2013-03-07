package jos.samples.animation.screens;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import jos.api.uikit.UIBarButtonItem;
import jos.api.uikit.UIInterfaceOrientation;
import jos.api.uikit.UIPopoverController;
import jos.api.uikit.UISplitViewController;
import jos.api.uikit.UISplitViewControllerDelegate;
import jos.api.uikit.UIView;
import jos.api.uikit.UIViewAnimationTransition;
import jos.api.uikit.UIViewController;
import jos.samples.animation.navigation.NavItem;
import jos.samples.animation.navigation.NavItemTableDelegate.RowClickListener;

public class MainSplitView extends UISplitViewController {

    MasterNavTableViewController masterView;
    UIViewController detailView;
    UIPopoverController popoverController;
    UIBarButtonItem rootPopoverButtonItem;

    public MainSplitView() {
        super();

        // create our master and detail views
        masterView = new MasterNavTableViewController();
        detailView = new BasicUIViewAnimationScreen();

        // create an array of controllers from them and then assign it to the
        // controllers property
        setViewControllers(new UIViewController[] { masterView,  detailView });

        // in this example, i expose an event on the master view called RowClicked, and i listen
        // for it in here, and then call a method on the detail view to update. this class thereby
        // becomes the defacto controller for the screen (both views).
        masterView.setRowClickListener(new RowClickListener() {
            @Override
            public void onEvent(NavItem item) {
                handleRowClicked(item);
            }
        });

        setDelegate(new UISplitViewControllerDelegate() {
            @Override
            public void willHideViewController(UISplitViewController controller, UIViewController aViewController, UIBarButtonItem barButtonItem, UIPopoverController pc) {
                // when the master view controller is hid (portrait mode), we add a button to
                // the detail view that when clicked will show the master view in a popover controller
                popoverController = pc;
                rootPopoverButtonItem = barButtonItem;
                ((IDetailView) detailView).addContentsButton(rootPopoverButtonItem);
            }
            @Override
            public void willShowViewController(UISplitViewController svc, UIViewController aViewController, UIBarButtonItem barButtonItem) {
                // when the master view controller is shown (landscape mode), remove the button
                // since the controller is shown.
                ((IDetailView) detailView).removeContentsButton();
                popoverController = null;
                rootPopoverButtonItem = null;
            }
        });
    }

    @Override
    public boolean shouldAutorotateToInterfaceOrientation(UIInterfaceOrientation orientation) {
        return true;
    }

    protected void handleRowClicked(NavItem item) {
        System.out.println("Changing Screens");

        if (popoverController != null) {
            popoverController.dismiss(true);
        }

        // if the nav item has a proper controller, push it on to the NavigationController
        // NOTE: we could also raise an event here, to loosely couple this, but isn't neccessary,
        // because we'll only ever use this this way
        if (item.getController() != null) {
            UIView.beginAnimations("DetailViewPush", null);
            detailView = item.getController();
            setViewControllers(new UIViewController[] { masterView,  detailView });
            UIView.setAnimationTransition(UIViewAnimationTransition.FLIP_FROM_RIGHT,
                    getViewControllers()[1].getView(), false);
            UIView.commitAnimations();
        } else {
            if (item.getControllerType() != null) {
                Constructor<? extends UIViewController> ctor = null;
                try {
                    ctor = item.getControllerType().getConstructor();
                } catch (SecurityException e1) {
                } catch (NoSuchMethodException e1) {
                }

                // if we found the constructor
                if (ctor != null) {
                    UIViewController instance = null;

                    try {
                        instance = (UIViewController) ctor.newInstance();
                    } catch (IllegalArgumentException e) {
                    } catch (InstantiationException e) {
                    } catch (IllegalAccessException e) {
                    } catch (InvocationTargetException e) {
                    }

                    if (instance != null) {
                        // save the object
                        item.setController(instance);

                        // push the view controller onto the stack
                        UIView.beginAnimations("DetailViewPush", null);
                        detailView = item.getController();
                        setViewControllers(new UIViewController[] { masterView,  detailView });
                        UIView.setAnimationTransition(UIViewAnimationTransition.FLIP_FROM_RIGHT,
                                getViewControllers()[1].getView(), false);
                        UIView.commitAnimations();
                    } else {
                        System.out.println("instance of view controller not created");
                    }
                } else {
                    System.out.println("constructor not found");
                }
            }
        }

        if (rootPopoverButtonItem != null) {
            ((IDetailView) detailView).addContentsButton(rootPopoverButtonItem);
        }
    }

}
