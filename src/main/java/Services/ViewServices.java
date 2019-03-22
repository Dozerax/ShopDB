package Services;

import View.ViewController;

public class ViewServices {

    private static ViewController view;

    private ViewServices() {
    }

    public static ViewController getView() {
        if (view == null) {
            view = new ViewController();
        }
        return view;
    }
}