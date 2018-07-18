package com.example.aguirresabino.ormliteexemplocrud.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aguirresabino.ormliteexemplocrud.R;
import com.j256.ormlite.dao.Dao;

import java.util.List;

public class LivroArrayAdapter extends ArrayAdapter<String> {

    private LayoutInflater layoutInflater;
    private List list;
    private Dao<LivroModel,Integer> livroDao;

    public LivroArrayAdapter(Context context, int resource, List objects, Dao<LivroModel, Integer> livroDao) {
        super(context, resource, objects);
        this.list = objects;
        this.livroDao = livroDao;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = layoutInflater.inflate(R.layout.list_view, parent, false);
        if (list.get(position).getClass().isInstance(new LivroModel())){
            LivroModel livroModel = (LivroModel) list.get(position);
            ((TextView)convertView.findViewById(R.id.titulo)).setText(livroModel.titulo);
            ((TextView)convertView.findViewById(R.id.autor)).setText(livroModel.autor);
        }
        return convertView;
    }
}
