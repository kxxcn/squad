package dev.kxxcn.app_squad.ui.main.list.matchlist;

import java.util.List;

import dev.kxxcn.app_squad.data.model.Information;
import dev.kxxcn.app_squad.ui.BasePresenter;
import dev.kxxcn.app_squad.ui.BaseView;
import dev.kxxcn.app_squad.util.Constants;

/**
 * Created by kxxcn on 2018-05-09.
 */

public interface MatchListContract {
	interface View extends BaseView<Presenter> {
		void showMatchList(List<Information> list);

		void showSuccessfullyRequested();

		void showUnuccessfullyRequested();
	}

	interface Presenter extends BasePresenter {
		void setFiltering(Constants.ListsFilterType requestType);

		void onLoadList();

		void onRequest(String to, String title, String message);
	}

	interface ItemClickListener {
		void onClick(int position);
	}
}
