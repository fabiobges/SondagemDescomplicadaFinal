package com.project.sondagemocr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.project.sondagemocr.OCR.GoogleVision;

import java.io.IOException;


public class PoliFragment extends Fragment implements View.OnClickListener{

    View view;
    static Bitmap bitmap;
    static String strEscritaPoli;
    ImageView imgEscrita;
    EditText edtPoli;
    ImageButton imgBtn, imgBtnEscrita;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.poli_fragment, null);

        imgEscrita = (ImageView) view.findViewById(R.id.imageEscrita);
        imgBtn = (ImageButton) view.findViewById(R.id.imageButtonTiraFoto);
        imgBtnEscrita = (ImageButton) view.findViewById(R.id.imageEscritaPoli);
        edtPoli = (EditText) view.findViewById(R.id.editTextPoli);

        edtPoli.setText(strEscritaPoli);
        imgEscrita.setImageBitmap(bitmap);
        imgBtnEscrita.setOnClickListener(this);
        imgBtn.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v == imgBtn){
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent,0);
        }else if(v == imgBtnEscrita){
            strEscritaPoli = GoogleVision.resposta;
            edtPoli.setText(strEscritaPoli);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                bitmap =(Bitmap) bundle.get("data");
                bitmap = GoogleVision.scaleBitmapDown(bitmap,1200);
                try {
                    if(bitmap != null) {
                        GoogleVision.callCloudVision(bitmap);
                        Log.i("Script:","Bitmap não é null: ");
                    }else{
                        Log.i("Script:","Bitmap é null");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgEscrita.setImageBitmap(bitmap);
            }
        }

    }

    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentPoli();
        bitmap = null;
        GoogleVision.resposta = null;
        super.onDestroy();
        Log.i("Script","onDestroy PoliFragment");
    }

}
