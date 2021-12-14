    package com.example.tp_dbwithroom;


    import android.content.Intent;
    import android.os.Bundle;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Toast;

    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.lifecycle.Observer;
    import androidx.lifecycle.ViewModelProviders;
    import androidx.recyclerview.widget.ItemTouchHelper;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.google.android.material.floatingactionbutton.FloatingActionButton;

    import java.util.List;

    public class MainActivity extends AppCompatActivity {
        private ViewModel viewModel;
        private RecyclerView mrecycl;
        public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mrecycl = findViewById(R.id.recyclerview);
            mrecycl.setLayoutManager(new LinearLayoutManager(this));
            mrecycl.setHasFixedSize(true);

            final Adapter adapter = new Adapter(this);
            mrecycl.setAdapter(adapter);
            viewModel = ViewModelProviders.of(MainActivity.this).get(ViewModel.class);
            // Get all the words from the database
            // and associate them to the adapter.
            viewModel.getAllContact().observe(this, new Observer<List<Contact>>() {
                @Override
                public void onChanged(List<Contact> words) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setMylist(words);
                    // Toast.makeText(MainActivity.this,"onchanged",Toast.LENGTH_SHORT).show();
                }
            });
            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                }
            });
            adapter.setOnItemClickListener(new Adapter.ClickListener()  {

                @Override
                public void onItemClick(View v, int position) {
                    Contact contact = adapter.getWordAtPosition(position);
                    viewModel.deleteContact(contact);
                }
            });

        }
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
               Contact contact = new Contact(data.getStringExtra("nom"),data.getStringExtra("prenom")
                       ,data.getStringExtra("num"),data.getStringExtra("adresses"));
                viewModel.insert(contact);
            }
        }
        }


