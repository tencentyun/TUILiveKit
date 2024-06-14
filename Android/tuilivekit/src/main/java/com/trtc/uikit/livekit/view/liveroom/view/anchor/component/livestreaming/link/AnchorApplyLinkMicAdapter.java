package com.trtc.uikit.livekit.view.liveroom.view.anchor.component.livestreaming.link;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.trtc.tuikit.common.imageloader.ImageLoader;
import com.trtc.uikit.livekit.R;
import com.trtc.uikit.livekit.manager.LiveController;
import com.trtc.uikit.livekit.state.operation.SeatState;

import java.util.concurrent.CopyOnWriteArrayList;

public class AnchorApplyLinkMicAdapter extends RecyclerView.Adapter<AnchorApplyLinkMicAdapter.ApplyLinkMicViewHolder> {

    private final LiveController                                  mLiveController;
    private final Context                                         mContext;
    private final CopyOnWriteArrayList<SeatState.SeatApplication> mData = new CopyOnWriteArrayList<>();

    public AnchorApplyLinkMicAdapter(Context context, LiveController liveController) {
        mContext = context;
        mLiveController = liveController;

        initData();
    }

    @NonNull
    @Override
    public ApplyLinkMicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.livekit_layout_anchor_link_mic_panel_item_request, parent, false);
        return new ApplyLinkMicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplyLinkMicViewHolder holder, int position) {
        if (TextUtils.isEmpty(mData.get(position).userName)) {
            holder.textName.setText(mData.get(position).userId);
        } else {
            holder.textName.setText(mData.get(position).userName);
        }

        if (TextUtils.isEmpty(mData.get(position).avatarUrl)) {
            holder.imageHead.setImageResource(R.drawable.livekit_ic_avatar);
        } else {
            ImageLoader.load(mContext, holder.imageHead, mData.get(position).avatarUrl, R.drawable.livekit_ic_avatar);
        }
        holder.textReject.setTag(mData.get(position));
        holder.textReject.setOnClickListener((view) -> {
            final SeatState.SeatApplication seatApplication = (SeatState.SeatApplication) view.getTag();
            mLiveController.getSeatController().rejectRequest(seatApplication.id);
        });

        holder.textAccept.setTag(mData.get(position));
        holder.textAccept.setOnClickListener((view) -> {
            final SeatState.SeatApplication seatApplication = (SeatState.SeatApplication) view.getTag();
            mLiveController.getSeatController().acceptRequest(seatApplication.id);
        });
    }

    private void initData() {
        mData.clear();
        mData.addAll(mLiveController.getSeatState().seatApplicationList.get());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData() {
        initData();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ApplyLinkMicViewHolder extends RecyclerView.ViewHolder {
        public ImageFilterView imageHead;
        public TextView        textName;
        public TextView        textLevel;
        public TextView        textAccept;
        public TextView        textReject;

        public ApplyLinkMicViewHolder(View itemView) {
            super(itemView);
            imageHead = itemView.findViewById(R.id.iv_head);
            textName = itemView.findViewById(R.id.tv_name);
            textLevel = itemView.findViewById(R.id.tv_level);
            textAccept = itemView.findViewById(R.id.tv_accept);
            textReject = itemView.findViewById(R.id.tv_reject);
        }
    }
}

