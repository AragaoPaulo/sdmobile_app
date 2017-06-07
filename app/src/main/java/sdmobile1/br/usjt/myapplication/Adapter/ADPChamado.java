package sdmobile1.br.usjt.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sdmobile1.br.usjt.myapplication.Model.Chamado;
import sdmobile1.br.usjt.myapplication.R;

/**
 * Created by Diego Sgarbi RA:201517012 & Paulo Aragão RA:201522680 - Grupo 4
 */
public class ADPChamado extends BaseAdapter {

    private List<Chamado> items = new ArrayList<Chamado>();
    private Context context;

    public ADPChamado (List<Chamado> items, Context context) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.adp_chamado, null);

        TextView lbTitulo = (TextView) view.findViewById(R.id.lbTitulo);

        lbTitulo.setText(items.get(position).getNmChamado());

        TextView lbStatus = (TextView) view.findViewById(R.id.lbStatus);

        lbStatus.setText(String.valueOf(items.get(position).getStatus()));

        TextView lbData = (TextView) view.findViewById(R.id.lbData);

        lbData.setText(items.get(position).getDtInclusao());

        return view;
    }
}
