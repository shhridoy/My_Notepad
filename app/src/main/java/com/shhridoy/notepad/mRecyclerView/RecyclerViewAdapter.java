package com.shhridoy.notepad.mRecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.AlignmentSpan;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shhridoy.notepad.AddNoteActivity;
import com.shhridoy.notepad.R;
import com.shhridoy.notepad.mDatabase.DatabaseHelper;

import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

/**
 * Created by whoami on 7/28/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ListItem> itemsList = null;
    private Context context;

    public RecyclerViewAdapter(Context context, List<ListItem> itemsList) {
        this.itemsList = itemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ListItem listItem = itemsList.get(position);
        holder.tvTitle.setText(listItem.getTitle());
        holder.tvDateTime.setText(listItem.getDate_time());
        holder.tvInvisibleID.setText(String.valueOf(listItem.getId()));

        holder.rlItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listItem.getId()+"\n"+listItem.getTitle()+"\n"+listItem.getDetails(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        TextView tvTitle, tvDateTime, tvInvisibleID;
        RelativeLayout rlItems;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.listItemTitleTV);
            tvDateTime = itemView.findViewById(R.id.listItemTimeDateTV);
            tvInvisibleID = itemView.findViewById(R.id.listItemInvisisbleIDTV);
            rlItems = itemView.findViewById(R.id.RLNotesListItem);
            itemView.setOnCreateContextMenuListener(this);
            //itemView.setOnClickListener(this);
        }

        /*@Override
        public void onClick(View v) {
            
        }*/

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle(tvTitle.getText().toString());
            MenuItem call = contextMenu.add(Menu.NONE, 1, 1, "Edit");
            MenuItem sms = contextMenu.add(Menu.NONE, 2, 2, "Delete");
            MenuItem addToFav = contextMenu.add(Menu.NONE, 3, 3, "Share");
            //groupId, itemId, order, title
            call.setOnMenuItemClickListener(onChange);
            sms.setOnMenuItemClickListener(onChange);
            addToFav.setOnMenuItemClickListener(onChange);
        }

        private final MenuItem.OnMenuItemClickListener onChange = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1:
                        Intent intent = new Intent(context, AddNoteActivity.class);
                        intent.putExtra("ID", Integer.parseInt(tvInvisibleID.getText().toString()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        return true;

                    case 2:
                        DatabaseHelper dbHelper = new DatabaseHelper(context);
                        boolean removed = dbHelper.removeNote(Integer.parseInt(tvInvisibleID.getText().toString()));
                        if (removed) {
                            itemsList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            Toast.makeText(context, "Note has been removed!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Note doesn't removed!!!", Toast.LENGTH_LONG).show();
                        }
                        return true;

                    case 3:
                        Toast.makeText(context, "This is sharing Intent.", Toast.LENGTH_LONG).show();
                        return true;
                }
                return false;
            }
        };
    }
    

}