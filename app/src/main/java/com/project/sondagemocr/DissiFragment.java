package com.project.sondagemocr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import android.widget.*;

public class DissiFragment extends Fragment implements View.OnClickListener{

    static Bitmap bitmap;
    ImageView imgEscrita;
    EditText edtDissi;
    ImageButton imgBtn;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup group, Bundle bundle){
        super.onCreateView(inflater,group,bundle);
        View view = inflater.inflate(R.layout.dissi_fragment,null);

        imgEscrita = (ImageView) view.findViewById(R.id.imageEscrita);
        imgBtn = (ImageButton) view.findViewById(R.id.imageButtonTiraFoto);

        imgEscrita.setImageBitmap(bitmap);
        imgBtn.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View v) {
        if(v == imgBtn){
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent,0);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            Bundle bundle = data.getExtras();
            if(bundle != null){
                bitmap =(Bitmap) bundle.get("data");
                imgEscrita.setImageBitmap(bitmap);
            }
        }

    }

    @Override
    public void onDestroy() {
        ((CadastroSondagemActivity)getActivity()).salvarDadosFragmentDissi();
        bitmap = null;
        super.onDestroy();
        Log.i("Script","onDestroy DissiFragment");
    }
}
