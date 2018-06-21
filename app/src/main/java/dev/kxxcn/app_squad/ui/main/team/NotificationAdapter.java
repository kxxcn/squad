package dev.kxxcn.app_squad.ui.main.team;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.kxxcn.app_squad.R;
import dev.kxxcn.app_squad.data.model.Notification;
import dev.kxxcn.app_squad.util.DialogUtils;

import static dev.kxxcn.app_squad.util.Constants.TYPE_COLLECTION;

/**
 * Created by kxxcn on 2018-06-14.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

	private List<Notification> mList;

	private TeamContract.ItemClickListener mItemClickListener;

	public NotificationAdapter(List<Notification> list, TeamContract.ItemClickListener itemClickListener) {
		this.mList = list;
		this.mItemClickListener = itemClickListener;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
		return new NotificationAdapter.ViewHolder(view, mItemClickListener);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.tv_message.setText(mList.get(holder.getAdapterPosition()).getMessage());
		if (mList.get(holder.getAdapterPosition()).getSender().equals(R.string.app_name)) {
			holder.ll_root.setOnClickListener(null);
			holder.ib_arrow.setVisibility(View.GONE);
			holder.iv_icon.setBackgroundResource(R.drawable.ic_admin);
		}

		if (Integer.parseInt(DialogUtils.getFormattedDate(DialogUtils.getDate(), TYPE_COLLECTION))
				> Integer.parseInt(mList.get(holder.getAdapterPosition()).getDate())) {
			holder.ll_root.setOnClickListener(null);
			holder.ib_arrow.setVisibility(View.GONE);
			int index = mList.get(holder.getAdapterPosition()).getTimestamp().indexOf(" ");
			holder.tv_time.setText(mList.get(holder.getAdapterPosition()).getTimestamp().substring(0, index));
		} else {
			holder.tv_time.setText(mList.get(holder.getAdapterPosition()).getTimestamp());
		}
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	static class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.ll_root)
		LinearLayout ll_root;
		@BindView(R.id.iv_icon)
		ImageView iv_icon;
		@BindView(R.id.tv_message)
		TextView tv_message;
		@BindView(R.id.tv_time)
		TextView tv_time;
		@BindView(R.id.ib_arrow)
		ImageButton ib_arrow;

		public ViewHolder(View itemView, final TeamContract.ItemClickListener itemClickListener) {
			super(itemView);
			ButterKnife.bind(this, itemView);
			ll_root.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					itemClickListener.onClick(getAdapterPosition());
				}
			});
		}
	}

}
