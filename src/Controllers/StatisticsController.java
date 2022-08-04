package Controllers;



import javafx.collections.FXCollections;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

import Views.StatisticsView;

public class StatisticsController {
    private StatisticsView statisticView;
    public StatisticsView getStatisticsView() {
		return statisticView;
	}
	public void setStatisticsView(StatisticsView statisticView) {
		this.statisticView = statisticView;
	}
	public StatisticsController(StatisticsView statisticsView) {
       setStatisticsView(statisticsView);
    }


}
