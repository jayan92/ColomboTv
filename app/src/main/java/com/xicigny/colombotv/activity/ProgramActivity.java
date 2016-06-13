package com.xicigny.colombotv.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dexafree.materialList.card.Card;
import com.dexafree.materialList.card.CardProvider;
import com.dexafree.materialList.card.OnActionClickListener;
import com.dexafree.materialList.card.action.TextViewAction;
import com.dexafree.materialList.listeners.RecyclerItemClickListener;
import com.dexafree.materialList.view.MaterialListView;
import com.xicigny.colombotv.R;

public class ProgramActivity extends AppCompatActivity {

    MaterialListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programs);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Programs");
        actionBar.setHomeButtonEnabled(true);

        mListView = (MaterialListView) findViewById(R.id.material_listview);

        mListView.addOnItemTouchListener(new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(Card card, int position) {
                Intent intent = new Intent(ProgramActivity.this,WebActivity.class);

                switch (position){
                    case 0:
                        intent.putExtra("query","Adaheraya");
                        startActivity(intent);
                        break;
                    case 1:
                        intent.putExtra("query","Ape Uruma Kama");
                        startActivity(intent);
                        break;
                    case 2:
                        intent.putExtra("query","Athinatha");
                        startActivity(intent);
                        break;
                    case 3:
                        intent.putExtra("query","Colombo Kitchen");
                        startActivity(intent);
                        break;
                    case 4:
                        intent.putExtra("query","Colombo Paththgara");
                        startActivity(intent);
                        break;
                    case 5:
                        intent.putExtra("query","Colombo Sajje");
                        startActivity(intent);
                        break;
                    case 6:
                        intent.putExtra("query","Colombo Top 10");
                        startActivity(intent);
                        break;
                    case 7:
                        intent.putExtra("query","Internet");
                        startActivity(intent);
                        break;
                    case 8:
                        intent.putExtra("query","Lassana Baila");
                        startActivity(intent);
                        break;
                    case 9:
                        intent.putExtra("query","Nadun Sewana");
                        startActivity(intent);
                        break;
                    case 10:
                        intent.putExtra("query","Charikawa");
                        startActivity(intent);
                        break;
                    case 11:
                        intent.putExtra("query","Ratu Katta");
                        startActivity(intent);
                        break;
                    case 12:
                        intent.putExtra("query","Sihina Maliga");
                        startActivity(intent);
                        break;
                    case 13:
                        intent.putExtra("query","Ras");
                        startActivity(intent);
                        break;
                    case 14:
                        intent.putExtra("query","Back Stage");
                        startActivity(intent);
                        break;
                    case 15:
                        intent.putExtra("query","Wedalla");
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onItemLongClick(Card card, int position) {
                //
            }
        });

        addPrograms();
    }

    public void addPrograms(){

        Card program1 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Adaheraya")
                .setDescription("Set a description")
                .setDrawable(R.drawable.adaheraya_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program2 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Ape Uruma Kama")
                .setDescription("Set a description")
                .setDrawable(R.drawable.ape_uruma_kama_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program3 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Athinatha")
                .setDescription("Set a description")
                .setDrawable(R.drawable.athinatha_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program4 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Colombo Kitchen")
                .setDescription("Set a description")
                .setDrawable(R.drawable.ck_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program5 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Colombo Paththgara")
                .setDescription("Set a description")
                .setDrawable(R.drawable.colombo_paththgara_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program6 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Colombo Sajje")
                .setDescription("Set a description")
                .setDrawable(R.drawable.colombo_sajje_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program7 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Colombo Top 10")
                .setDescription("Set a description")
                .setDrawable(R.drawable.colombo_top_ten_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program8 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Internet")
                .setDescription("Set a description")
                .setDrawable(R.drawable.internet_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program9 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Lassana Baila")
                .setDescription("Set a description")
                .setDrawable(R.drawable.lassana_baila_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program10 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Nadun Sewana")
                .setDescription("Set a description")
                .setDrawable(R.drawable.nadun_sewana_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program11 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Charikawa")
                .setDescription("Set a description")
                .setDrawable(R.drawable.program_charikawa)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program12 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Ratu Katta")
                .setDescription("Set a description")
                .setDrawable(R.drawable.ratu_katta_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program13 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Sihina Maliga")
                .setDescription("Set a description")
                .setDrawable(R.drawable.sihina_maliga_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program14 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Ras")
                .setDescription("Set a description")
                .setDrawable(R.drawable.t_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program15 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Back Stage")
                .setDescription("Set a description")
                .setDrawable(R.drawable.th_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        Card program16 = new Card.Builder(this)
                .setTag("BIG_IMAGE_CARD")
                .withProvider(new CardProvider())
                .setLayout(R.layout.material_big_image_card_layout)
                .setTitle("Wedalla")
                .setDescription("Set a description")
                .setDrawable(R.drawable.wedalla_colombo_tv)
                .setTitleColor(getResources().getColor(R.color.white))
                .endConfig()
                .build();

        mListView.getAdapter().add(program1);
        mListView.getAdapter().add(program2);
        mListView.getAdapter().add(program3);

        mListView.getAdapter().add(program4);
        mListView.getAdapter().add(program5);
        mListView.getAdapter().add(program6);

        mListView.getAdapter().add(program7);
        mListView.getAdapter().add(program8);
        mListView.getAdapter().add(program9);

        mListView.getAdapter().add(program10);
        mListView.getAdapter().add(program11);
        mListView.getAdapter().add(program12);

        mListView.getAdapter().add(program13);
        mListView.getAdapter().add(program14);
        mListView.getAdapter().add(program15);

        mListView.getAdapter().add(program16);

    }

}
