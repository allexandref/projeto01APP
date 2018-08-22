package br.unipe.projeto01app.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

import br.unipe.projeto01app.R;
import br.unipe.projeto01app.model.Pessoa;

public class AdapterPessoa extends ArrayAdapter<Pessoa> {

    private Context context;
    private List<Pessoa> pessoas;

    public AdapterPessoa(@NonNull Context context, @LayoutRes int resource, @NonNull List<Pessoa> objects) {
        super(context, resource, objects);
        this.context = context;
        this.pessoas = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup viewGroup){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listar, viewGroup, false);

        TextView id = (TextView) rowView.findViewById(R.id.txIdPessoa);
        TextView nome = (TextView) rowView.findViewById(R.id.txNomePessoa);
        TextView sobrenome = (TextView) rowView.findViewById(R.id.txSobrenomePessoa);

        id.setText(String.format("Id: %d", pessoas.get(pos).getId()));
        nome.setText(String.format("Nome: %s", pessoas.get(pos).getFirstName()));
        sobrenome.setText(String.format("Sobrenome: %s", pessoas.get(pos).getLastName()));

        return rowView;
    }
}
