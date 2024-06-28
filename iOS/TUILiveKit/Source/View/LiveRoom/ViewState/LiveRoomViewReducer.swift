//
//  LiveRoomViewReducer.swift
//  TUILiveKit
//
//  Created by aby on 2024/5/28.
//

import Foundation

let liveRoomMenuReducer = Reducer<MenuState>(
    ReduceOn(LiveRoomViewActions.updateBottomMenus, reduce: { state, action in
        let creator = BottomPopupListViewDataHelper()
        state.menusButtons = creator.generateAudienceBottomMenuData(store: action.payload.0,
                                                                    routerStore: action.payload.1)
    })
)
