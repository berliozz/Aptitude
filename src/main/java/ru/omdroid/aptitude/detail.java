package ru.omdroid.aptitude;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by berliozz on 02.02.14.
 */
public class detail extends Fragment {
    private final int descTextID;
    private final String descNegative;
    private final String descPositive;
    private final int descRating;

    public detail(int descTextID, String descNegative, int descRating, String descPositive) {
        this.descTextID = descTextID;
        this.descRating = descRating;
        this.descNegative = descNegative;
        this.descPositive = descPositive;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        TextView textView, txtRating, txtNegative, txtPositive;
        textView = (TextView)view.findViewById(R.id.txtTest);
        txtRating = (TextView)view.findViewById(R.id.txtRating);
        txtNegative = (TextView)view.findViewById(R.id.txtNegative);
        txtPositive = (TextView)view.findViewById(R.id.txtPositive);

        txtNegative.setText(String.valueOf(descNegative));
        txtRating.setText(String.valueOf(descRating));
        txtPositive.setText(String.valueOf(descPositive));
        textView.setText(getString(descTextID));
        return view;
    }
}
