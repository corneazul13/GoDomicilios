package godomicilios.mdcc.pulpapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import godomicilios.mdcc.pulpapp.settings.chats;
import java.util.ArrayList;

import godomicilios.mdcc.pulpapp.R;
import godomicilios.mdcc.pulpapp.settings.settings;

/**
 * Created by PROGRAMACION5 on 25/08/2017.
 */

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.AdapterView>{
    private Context context;
    private ArrayList<chats> chatList= new ArrayList<>();


    public chatAdapter(Context context, ArrayList<chats> chatList) {
        this.context = context;
        this.chatList = chatList;
    }
    @Override
    public chatAdapter.AdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_out,parent,false);
        return new AdapterView(view);

    }

    @Override
    public void onBindViewHolder(chatAdapter.AdapterView holder, int position) {
        chats chat= chatList.get(position);
        String person = chat.getPerson();
        String message = chat.getMessage();
        if (person.equalsIgnoreCase("SUCURSAL")){
            holder.contentChatIn.setVisibility(View.VISIBLE);
            holder.textIn.setText(message);
        }
        else if(person.equalsIgnoreCase("CLIENTE")){
            holder.contentChatOut.setVisibility(View.VISIBLE);
            holder.textOut.setText(message);
        }

    }

    @Override
    public int getItemCount() {

        return chatList.size();
    }

    public class AdapterView extends RecyclerView.ViewHolder{

        private LinearLayout contentChatIn;
        private LinearLayout contentChatOut;
        private TextView textIn;
        private TextView textOut;

        public AdapterView(View itemView) {
            super(itemView);
            contentChatIn = (LinearLayout) itemView.findViewById(R.id.contentChatIn);
            contentChatOut = (LinearLayout) itemView.findViewById(R.id.contentChatOut);
            textIn = (TextView) itemView.findViewById(R.id.textIn);
            textOut = (TextView) itemView.findViewById(R.id.textOut);
        }
    }
}
