package com.project.sondagemocr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.*;

import com.project.sondagemocr.OCR.GoogleVision;

import java.io.IOException;

public class DissiFragment extends Fragment implements View.OnClickListener{

    static Bitmap bitmapDi;
    static String strEscritaDissi;
    ImageView imgEscrita;
    static public EditText edtDissi;
    ImageButton imgBtn, imgBtnEscrita;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup group, Bundle bundle){
        super.onCreateView(inflater,group,bundle);
        View view = inflater.inflate(R.layout.dissi_fragment,null);

        imgEscrita = (ImageView) view.findViewById(R.id.imageEscrita);
        imgBtn = (ImageButton) view.findViewById(R.id.imageButtonTiraFoto);
        imgBtnEscrita = (ImageButton) view.findViewById(R.id.imageEscritaDi);
        edtDissi = (EditText) view.findViewById(R.id.editTextDissi);

        edtDissi.setText(strEscritaDissi);
        imgBtnEscrita.setOnClickListener(this);
        imgEscrita.setImageBitmap(bitmapDi);
        imgBtn.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v == imgBtn){
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent,0);
        }else if(v == imgBtnEscrita){
            strEscritaDissi = GoogleVision.resposta;
            if(strEscritaDissi != null) {
                edtDissi.setText(strEscritaDissi.replaceAll(" ", ""));
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                bitmapDi =(Bitmap) bundle.get("data");
                bitmapDi = GoogleVision.scaleBitmapDown(bitmapDi,1200);
                try {
                    if(bitmapDi != null) {
                        GoogleVision.callCloudVision(bitmapDi);
                        Log.i("Script:","Bitmap não é null: ");
                    }else{
                        Log.i("Script:","Bitmap é null");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgEscrita.setImageBitmap(bitmapDi);
            }
        }

    }
/*
    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentDissi();
        bitmap = null;
        GoogleVision.resposta = null;
        super.onDestroy();
        Log.i("Script","onDestroy DissiFragment");
    }*/
}
