package it.italiandudes.mymcserver.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import java.util.ArrayList;

import it.italiandudes.mymcserver.Constants;
import it.italiandudes.mymcserver.R;
import it.italiandudes.mymcserver.threads.ServerInfoThread;
import it.italiandudes.mymcserver.utils.adapters.InfoAddonsListAdapter;
import it.italiandudes.mymcserver.utils.adapters.InfoPlayersListAdapter;
import it.italiandudes.mymcserver.utils.models.AddonsDataRow;
import it.italiandudes.mymcserver.utils.models.PlayersDataRow;

public class InfoActivity extends Activity {

    private ServerInfoThread infoThread;
    private AnyChartView cpuChart;
    private AnyChartView ramTotalToFreeChart;
    private AnyChartView ramCommittedChart;
    private ListView playersListView;
    private ArrayList<PlayersDataRow> playersList;
    private ListView addonsListView;
    private ArrayList<AddonsDataRow> addonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        playersList = new ArrayList<>();
        addonsList = new ArrayList<>();

        cpuChart = findViewById(R.id.cpuPieGraph);
        ramTotalToFreeChart = findViewById(R.id.ramTotalToFreePieGraph);
        ramCommittedChart = findViewById(R.id.ramCommittedPieGraph);
        playersListView = findViewById(R.id.playersList);
        playersListView.setAdapter(new InfoPlayersListAdapter(getApplicationContext(),playersList));
        addonsListView = findViewById(R.id.addonsList);
        addonsListView.setAdapter(new InfoAddonsListAdapter(getApplicationContext(),addonsList));

        infoThread = new ServerInfoThread(this);
        infoThread.start();
    }

    public void toTerminal(View view){

        new Thread(()->{
            infoThread.setInterrupted(true);

            runOnUiThread(()->{
                Intent switchTerminal = new Intent(this, TerminalActivity.class);
                startActivity(switchTerminal);
            });
        }).start();
    }

    public void drawCPUGraph(double systemCpuLoad, double processCpuLoad){
        Pie cpuPieChart = AnyChart.pie();

        double freeCpu = 100.0 - (systemCpuLoad+processCpuLoad);

        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Prova 1",10));
        data.add(new ValueDataEntry("Prova 2",80));
        data.add(new ValueDataEntry("Prova 3",10));
        /*data.add(new ValueDataEntry(getString(R.string.string_info_graphs_section_cpu_free),freeCpu));
        data.add(new ValueDataEntry(getString(R.string.string_info_graphs_section_cpu_system),systemCpuLoad));
        data.add(new ValueDataEntry(getString(R.string.string_info_graphs_section_cpu_process),processCpuLoad));*/

        cpuPieChart.data(data);

        cpuPieChart.labels().position("outside");

        cpuPieChart.legend().position("center-bottom").itemsLayout(LegendLayout.HORIZONTAL).align(Align.CENTER);

        cpuChart.setChart(cpuPieChart);

        Log.d(Constants.Log.TAG,"CPU Pie chart set");
    }

    public void drawRAMGraphs(double totalMemory, double freeMemory, double committedVirtualMemory){
        Pie ramTotalToFreePieChart = AnyChart.pie();
        Pie ramCommittedPieChart = AnyChart.pie();

        //TotalToFree Pie Chart data calculations
        double occupiedRAM = totalMemory-freeMemory;

        ArrayList<DataEntry> dataTotalToFree = new ArrayList<>();
        dataTotalToFree.add(new ValueDataEntry(getString(R.string.string_info_graphs_section_ram_free),freeMemory));
        dataTotalToFree.add(new ValueDataEntry(getString(R.string.string_info_graphs_section_ram_occupied),occupiedRAM));

        ramTotalToFreePieChart.data(dataTotalToFree);

        ramTotalToFreePieChart.labels().position("outside");

        ramTotalToFreePieChart.legend().position("center-bottom").itemsLayout(LegendLayout.HORIZONTAL).align(Align.CENTER);

        ramTotalToFreeChart.setChart(ramTotalToFreePieChart);

        Log.d(Constants.Log.TAG,"RAM Total To Free Pie chart set");

        //Committed Pie Chart data calculations
        double freeVirtualMemory = occupiedRAM-committedVirtualMemory;

        ArrayList<DataEntry> dataCommitted = new ArrayList<>();
        dataTotalToFree.add(new ValueDataEntry(getString(R.string.string_info_graphs_section_ram_virtual_free),freeVirtualMemory));
        dataTotalToFree.add(new ValueDataEntry(getString(R.string.string_info_graphs_section_ram_virtual_committed),committedVirtualMemory));

        ramCommittedPieChart.data(dataCommitted);

        ramCommittedPieChart.labels().position("outside");

        ramCommittedPieChart.legend().position("center-bottom").itemsLayout(LegendLayout.HORIZONTAL).align(Align.CENTER);

        ramCommittedChart.setChart(ramCommittedPieChart);

        Log.d(Constants.Log.TAG,"RAM Committed Pie chart set");
    }

    public void updatePlayersList(PlayersDataRow dataRow){
        playersList.add(dataRow);

        ((ArrayAdapter<PlayersDataRow>)playersListView.getAdapter()).notifyDataSetChanged();
    }

    public void updateAddonsList(AddonsDataRow dataRow){
        addonsList.add(dataRow);

        ((ArrayAdapter<AddonsDataRow>)addonsListView.getAdapter()).notifyDataSetChanged();
    }
}