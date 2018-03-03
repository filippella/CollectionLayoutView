package org.dalol.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import org.dalol.collectionlayoutview.CollectionLayoutView
import org.dalol.collectionlayoutview.adapter.CollectionViewAdapter
import org.dalol.collectionlayoutview.callback.OnListItemClickListener

class MainActivity : AppCompatActivity() {

    lateinit var clv : CollectionLayoutView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = mutableListOf<String>();

        for (i in 1..100) {
            items.add("Item -> " + i);
        }

        clv = findViewById(R.id.collectionview)


        val adapter: CollectionViewAdapter<String, SingleItemViewHolder> = object : CollectionViewAdapter<String, SingleItemViewHolder>(this) {
            override fun onCreateViewHolder(layoutInflater: LayoutInflater?, parent: ViewGroup?, viewType: Int): SingleItemViewHolder {
                return SingleItemViewHolder(layoutInflater?.inflate(R.layout.item_simple_layout, parent, false))
            }
        }
        adapter.setItemClickListener(object : OnListItemClickListener<String> {
            override fun onItemClick(view: View?, item: String?, position: Int) {
                Toast.makeText(applicationContext, item, Toast.LENGTH_SHORT).show()
            }
        } )

        //clv.setCollectionAsList(adapter, items, 4, 50);

        clv.setCollectionAsPager(adapter, items, 4, 3, 50)
    }
}
