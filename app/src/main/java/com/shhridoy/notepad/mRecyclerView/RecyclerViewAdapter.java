package com.shhridoy.notepad.mRecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shhridoy.notepad.AddNoteActivity;
import com.shhridoy.notepad.R;
import com.shhridoy.notepad.ViewNoteActivity;
import com.shhridoy.notepad.mDatabase.DBAdapter;
import com.shhridoy.notepad.mDialogs.MyDialogs;
import com.shhridoy.notepad.mUtilities.MyPreferences;

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
        View view = null;
        if (MyPreferences.getPreference(context, "Notes View").equalsIgnoreCase("Small list")) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_small, parent, false);
        } else if (MyPreferences.getPreference(context, "Notes View").equalsIgnoreCase("Medium list")) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_medium, parent, false);
        } else if (MyPreferences.getPreference(context, "Notes View").equalsIgnoreCase("List with details")) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_large, parent, false);
        } else if (MyPreferences.getPreference(context, "Notes View").equalsIgnoreCase("Grid")) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_large, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_medium, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ListItem listItem = itemsList.get(position);

        // EXTRACTING THE DATE
        String[] fullDateTime = listItem.getDate_time().split(" ");
        String date = null;
        try {
            date = fullDateTime[0] + " " + fullDateTime[1] + " " + fullDateTime[2];
        } catch (ArrayIndexOutOfBoundsException e) {
            date = listItem.getDate_time();
            e.printStackTrace();
        }

        holder.tvTitle.setText(listItem.getTitle());

        // FOR ITEM VIEW DETAILS AND GRID
        if (MyPreferences.getPreference(context, "Notes View").equalsIgnoreCase("List with details") ||
                MyPreferences.getPreference(context, "Notes View").equalsIgnoreCase("Grid")) {

            holder.tvDetails.setText(listItem.getDetails());
        }

        holder.tvDateTime.setText(date);
        holder.tvInvisibleID.setText(String.valueOf(listItem.getId()));

        holder.rlItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listItem.getLock() == 0) {
                    Intent intent = new Intent(context, ViewNoteActivity.class);
                    intent.putExtra("ID", String.valueOf(listItem.getId()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } else {
                    MyDialogs.inputPassword(context, listItem.getId(), "View Note");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        TextView tvTitle, tvDateTime, tvInvisibleID;
        TextView tvDetails; // FOR LIST VIEW WITH DETAILS
        RelativeLayout rlItems;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.listItemTitleTV);
            tvDateTime = itemView.findViewById(R.id.listItemTimeDateTV);
            tvInvisibleID = itemView.findViewById(R.id.listItemInvisisbleIDTV);
            rlItems = itemView.findViewById(R.id.RLNotesListItem);
            itemView.setOnCreateContextMenuListener(this);

            tvDetails = itemView.findViewById(R.id.listItemDetailsTV); // FOR LIST VIEW WITH DETAILS

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
                        intent.putExtra("ID", tvInvisibleID.getText().toString());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        return true;

                    case 2:
                        boolean removed = DBAdapter.removeNoteFromDB(context, Integer.parseInt(tvInvisibleID.getText().toString()));
                        if (removed) {
                            itemsList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
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