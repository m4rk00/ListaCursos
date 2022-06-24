package com.example.listacursos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.example.listacursos.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ActivityMainBinding binding;

    private List<ItemList> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WearableRecyclerView recyclerView = (WearableRecyclerView)
                findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setEdgeItemsCenteringEnabled(true);

        WearableLinearLayoutManager layoutManager =
                new WearableLinearLayoutManager(this);

        layoutManager.setOrientation(WearableLinearLayoutManager.VERTICAL);
        layoutManager.setLayoutCallback(new CustomScrollingLayoutCallback());

        recyclerView.setLayoutManager(layoutManager);

        //Items
        items.add(new ItemList(R.drawable.flutter, "Flutter","Curso de Flutter"));
        items.add(new ItemList(R.drawable.java, "Java","Curso de Java"));
        items.add(new ItemList(R.drawable.php, "PhP","Curso de PhP"));
        items.add(new ItemList(R.drawable.javascript, "JavaScript","Curso de JavaScript"));
        items.add(new ItemList(R.drawable.cplus, "CPlus","Curso de CPlus"));

        /* binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/
    }
}

class CustomScrollingLayoutCallback extends WearableLinearLayoutManager.LayoutCallback{
    private static final float MAX_ICON_PROGRESS = 0.65f;

    private float progressToCenter;

    @Override
    public void onLayoutFinished(View child, RecyclerView parent) {

        // Figure out % progress from top to bottom
        float centerOffset = ((float) child.getHeight() / 2.0f) / (float) parent.getHeight();
        float yRelativeToCenterOffset = (child.getY() / parent.getHeight()) + centerOffset;

        // Normalize for center
        progressToCenter = Math.abs(0.5f - yRelativeToCenterOffset);
        // Adjust to the maximum scale
        progressToCenter = Math.min(progressToCenter, MAX_ICON_PROGRESS);

        child.setScaleX(1 - progressToCenter);
        child.setScaleY(1 - progressToCenter);

    }

}//close customscrolling

class ItemList{
    final private int imageItem;
    final private String nameItem;
    final private String description;

    public ItemList(int _imageItem, String _nameItem, String _description){
        this.imageItem = _imageItem;
        this.nameItem = _nameItem;
        this.description = _description;
    }

    public int getImageItem() { return imageItem; }
    public String getNameItem() { return nameItem; }
    public String getDescription() { return description; }
}//close itemlist