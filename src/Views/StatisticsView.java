package Views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

import Controllers.StatisticsController;
import Models.Book;
import Models.Order;

public class StatisticsView extends View{
    private final HBox hBox = new HBox();
    private ArrayList<Book> books=Book.getBooks();


    public StatisticsView() {
        new StatisticsController(this);
    }

    @Override
    public Parent getView() {
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);

        ArrayList<Order> orders = Order.getOrders();
        ArrayList<Order> totalOrders = new ArrayList<>();  
        ArrayList<Order> groupOrder =new ArrayList<>();
        int[] counter= new int[orders.size()];
       
        for(int i=0;i<books.size();i++) {
        	int counter1=0;
        	
        	for(int j=0;j<orders.size();j++)
        	{
        		if(orders.get(j).getIsbn().matches(books.get(i).getIsbn())) {
        		
        			counter[i]=counter1+1;
        			counter1=counter[i];
        		}
        	}
        	
        }
    
       double costs=0;
       for(int i=0;i<books.size();i++) {
    	   costs=costs+(books.get(i).getQuantity()*books.get(i).getPurchasedPrice());
       }
       double profits=0;
       for(int i=0;i<orders.size();i++) {
    	   profits=profits+(orders.get(i).getSellingPrice());
    	   
       }
       profits=profits/2;
       
       
       CategoryAxis xAxis = new CategoryAxis();
       NumberAxis yAxis=new NumberAxis();
       
       BarChart<String,Double> barChart= new BarChart(xAxis,yAxis);
       XYChart.Series dataSeries1 = new XYChart.Series();
       dataSeries1.setName("Total Profits");

       dataSeries1.getData().add(new XYChart.Data("Total Costs", costs));
       dataSeries1.getData().add(new XYChart.Data("Total Profits"  , profits));
      
       	barChart.getData().add(dataSeries1);
       	
       	
        
        

   

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
                  
        for(int i=0;i<books.size();i++) {
        	pieChartData.add(new PieChart.Data(books.get(i).getTitle(), (100/books.size())*(counter[i])/2));
        }

        final PieChart chart = new PieChart(pieChartData);

        chart.setTitle("Sold Books");

        hBox.getChildren().addAll(chart,barChart);

        return hBox;
    }


}