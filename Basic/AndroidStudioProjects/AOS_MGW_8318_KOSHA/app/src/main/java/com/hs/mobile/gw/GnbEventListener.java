/**
 * @Autor tkofs
 * @date 20.06.23
 */

package com.hs.mobile.gw;

class GnbEventListener {
    public interface MyEventListener {
        void onGnbEvent(int group, int index);
    }

    public interface QuickEventListener {
        void onQuickEvent(String mid);
    }
}
