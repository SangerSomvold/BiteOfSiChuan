package com.foomap.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.foodmap.R;
import com.foomap.model.CommentData;
import com.foomap.service.CommentHttpService;
import com.foomap.service.HttpServiceHelper.IOnHttpRequeseListener;
import com.foomap.util.CommentJsonUtil;
import com.foomap.util.DensityUtil;
import com.foomap.util.ImageManager;
import com.foomap.view.lib.PullToRefreshBase;
import com.foomap.view.lib.PullToRefreshBase.Mode;
import com.foomap.view.lib.PullToRefreshBase.OnRefreshListener;
import com.foomap.view.lib.PullToRefreshListView;

public class PoiCommentView extends LinearLayout {
	private Context context;
	private static String TAG = "PoiCommentView";
	// 数据请求
	private CommentHttpService chHttpService;
	// listComment内容
	private PullToRefreshListView commentList;
	private ArrayList<CommentData> commentListData;
	private CommentListAdapter listAdapter;
	// 消费 评价等级
	private TextView userCost;
	private RatingBar userGrade;
	//
	private int pageNumber = 1;
	private int shopId;
	// 发挥数据监听
	private CommentRequestListener listener;

	// list滚动状态
	private boolean isBusy = false;
	//
	private boolean isRefreshing = false;
	private boolean isFirst = true;

	public PoiCommentView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		this.context = context;
		inflate(context, R.layout.comment_poidetail, this);
		init();
	}

	public void init() {
		userGrade = (RatingBar) findViewById(R.id.userGrade_poiDetail);
		userCost = (TextView) findViewById(R.id.userCost_poiDetail);

		listener = new CommentRequestListener();
		listAdapter = new CommentListAdapter();
		commentListData = null;
		commentList = (PullToRefreshListView) findViewById(R.id.commentList_poiDetail);
		commentList.setMode(Mode.PULL_FROM_END);
		commentList.setOnScrollListener(new CommentScrollListener());
		commentList.setAdapter(listAdapter);
		commentList.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				if (chHttpService != null && !isRefreshing) {
					isRefreshing = true;
					chHttpService.getComments(shopId, pageNumber, listener);

				} else {
					commentList.onRefreshComplete();
				}
			}
		});
	}

	public void refreash(int shopId) {
		this.shopId = shopId;
		if (chHttpService == null) {
			chHttpService = new CommentHttpService(context);
		}
		if (commentListData == null) {
			commentListData = new ArrayList<CommentData>();
		}
		isRefreshing = true;
		// 请求
		chHttpService.getComments(shopId, 1, listener);

	}

	// 服务器返回数据处理
	private class CommentRequestListener implements IOnHttpRequeseListener {

		@Override
		public void finished(String jsonRes) {
			// TODO Auto-generated method stub
			// 返回数据处理
			if (jsonRes == null) {
				isRefreshing = false;
				commentList.onRefreshComplete();
				return;
			}

			Log.i(TAG, jsonRes);
			int oldDataSize = commentListData.size();
			ArrayList<CommentData> tmpList = CommentJsonUtil
					.getComentList(jsonRes);
			int remainder = commentListData.size() % 10;
			Log.i(TAG, "SIZE------->" + commentListData.size() + "");
			if (tmpList != null && tmpList.size() > 0) {
				if (remainder == 0) {
					commentListData.addAll(tmpList);
				} else {
					// 删除最后一页数据

					for (int i = 0; i < remainder; i++) {
						int size = commentListData.size();
						commentListData.remove(size - 1);
					}
					commentListData.addAll(tmpList);
				}
				if (commentListData.size() % 10 == 0) {
					pageNumber++;
				}
				// 刷新界面
				commentList.onRefreshComplete();
				listAdapter.notifyDataSetChanged();

			}
			// 无数据
			if (oldDataSize == commentListData.size()) {
				Log.i(TAG, "isFirst" + isFirst);

				if (!isFirst) {
					Toast.makeText(context, "没有评论了亲", Toast.LENGTH_SHORT)
							.show();
				}
				commentList.onRefreshComplete();
			}
			isRefreshing = false;
			isFirst = false;

		}

	}

	// commentList Adapter
	private class CommentListAdapter extends BaseAdapter {
		private final int VIEW_TYPE_COUNT = 2;
		private final int WITH_PIC = 0;
		private final int WITHOUT_PIC = 1;
		LayoutInflater lf = LayoutInflater.from(context);

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (commentListData == null) {
				return 0;
			}
			return commentListData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public int getItemViewType(int position) {
			// TODO Auto-generated method stub
			List<String> picsUrl = commentListData.get(position).picUrl;
			if (picsUrl != null && picsUrl.size() > 0) {
				return WITH_PIC;
			} else {
				return WITHOUT_PIC;
			}
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return VIEW_TYPE_COUNT;
		}

		@Override
		public View getView(int position, View container, ViewGroup arg2) {
			// TODO Auto-generated method stub
			int type = getItemViewType(position);
			ViewHolder holder = null;

			if (container == null) {
				holder = new ViewHolder();
				if(type==WITH_PIC)
				{
					container = lf.inflate(R.layout.comment_item_pic_poidetail,
							null);
				}
				else if(type==WITHOUT_PIC)
				{
					container = lf.inflate(R.layout.comment_item_poidetail,
							null);
				}
			
				holder.comment_tx = (TextView) container
						.findViewById(R.id.usr_comment_poiDetail);
				holder.date_tx = (TextView) container
						.findViewById(R.id.comment_date_poiDetail);
				holder.userId_tx = (TextView) container
						.findViewById(R.id.usr_name_poiDetail);
				holder.cost_tx = (TextView) container
						.findViewById(R.id.userCost_poiDetail);
				holder.grade_rb = (RatingBar) container
						.findViewById(R.id.userGrade_poiDetail);

				if (type == WITH_PIC) {
					holder.commentPic = (ImageView) container
							.findViewById(R.id.omment_pic_poidetail);

				}
				container.setTag(holder);

			} else {
				holder = (ViewHolder) container.getTag();

			}

			holder.userId_tx.setText(commentListData.get(position).userId + "");
			holder.date_tx.setText(commentListData.get(position).commentTime
					+ "");
			holder.comment_tx.setText(commentListData.get(position).comment
					+ "");
			holder.grade_rb
					.setRating((float) commentListData.get(position).grade);
			holder.cost_tx.setText("¥" + commentListData.get(position).cost
					+ "");
			if (type == WITH_PIC) {
				ImageManager.Load(commentListData.get(position).picUrl.get(0), holder.commentPic, ImageManager.options);
			}

			return container;
		}

		private class ViewHolder {
			public TextView userId_tx, date_tx, comment_tx, cost_tx;
			public RatingBar grade_rb;
			public ImageView commentPic;
		}

	}

	// 判定listView 滚动状态
	public class CommentScrollListener implements OnScrollListener {

		@Override
		public void onScroll(AbsListView arg0, int arg1, int arg2, int arg3) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onScrollStateChanged(AbsListView arg0, int scrollState) {
			// TODO Auto-generated method stub
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_IDLE:// 空闲状态
				isBusy = false;

				break;
			case OnScrollListener.SCROLL_STATE_FLING:// 滚动状态
				isBusy = true;

				break;
			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 触摸后滚动
				isBusy = false;

				break;
			default:
				break;
			}

		}

	}

}
