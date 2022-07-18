package com.tencent.liteav.liveroom;

import android.content.Context;

public abstract class TUILiveRoom {

    public static TUILiveRoom sharedInstance(Context context) {
        return TUILiveRoomImpl.sharedInstance(context);
    }

    /**
     * CreateRoom
     *
     * @param roomId   generated by the server
     * @param name     room's name
     * @param coverUrl room's cover
     */
    public abstract void createRoom(int roomId, String name, String coverUrl);

    /**
     * EnterRoom
     *
     * @param roomId generated by the server
     */
    public abstract void enterRoom(String roomId);

    /**
     * Set the event callbacks of the component
     *
     * @param listener listener
     */
    public abstract void setListener(TUILiveRoomListener listener);
}