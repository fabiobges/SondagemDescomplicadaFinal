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

public class TriFragment extends Fragment implements View.OnClickListener{

    View view;
    static Bitmap bitmapTri;
    static String strEscritaTri;
    ImageView imgEscrita;
    public static EditText edtTri;
    ImageButton imgBtn, imgBtnEscrita;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.tri_fragment, null);

        imgEscrita = (ImageView) view.findViewById(R.id.imageEscrita);
        imgBtn = (ImageButton) view.findViewById(R.id.imageButtonTiraFoto);
        //imgBtnEscrita = (ImageButton) view.findViewById(R.id.imageEscritaTri);
        edtTri = (EditText) view.findViewById(R.id.editTextTri);

        edtTri.setText(strEscritaTri);
        //imgBtnEscrita.setOnClickListener(this);
        imgEscrita.setImageBitmap(bitmapTri);
        imgBtn.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v == imgBtn){
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent,0);
        }else if(v == imgBtnEscrita){
            strEscritaTri = GoogleVision.resposta;
            if(strEscritaTri != null) {
                edtTri.setText(strEscritaTri.replaceAll(" ", ""));
            }
        }
    }
    //Chamando Google Vision

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                bitmapTri =(Bitmap) bundle.get("data");
//                bitmapTri = GoogleVision.scaleBitmapDown(bitmapTri,1200);
//                try {
//                    if(bitmapTri != null) {
//                        GoogleVision.callCloudVision(bitmapTri);
//                        Log.i("Script:","Bitmap não é null: ");
//                    }else{
//                        Log.i("Script:","Bitmap é null");
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                imgEscrita.setImageBitmap(bitmapTri);
            }
        }

    }

/*
    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentTri();
        bitmap = null;
        GoogleVision.resposta = null;
        super.onDestroy();
        Log.i("Script","onDestroy TriFragment");
    }*/
}
