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



public class MonoFragment extends Fragment implements View.OnClickListener{

    ImageButton imgBtn,imgBtnEscrita;
    public static EditText edtTextMono;
    View view;
    ImageView imgEscrita;
    static Bitmap bitmapMono;
    static String strEscritaMono;



    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup group, Bundle bundle){
        super.onCreateView(inflater,group,bundle);
        view = inflater.inflate(R.layout.mono_fragment,null);

        imgEscrita = (ImageView) view.findViewById(R.id.imageEscrita);
        imgEscrita.setImageBitmap(bitmapMono);
        imgBtn = (ImageButton) view.findViewById(R.id.imageButtonTiraFoto);
        imgBtn.setOnClickListener(this);
        //imgBtnEscrita = (ImageButton) view.findViewById(R.id.imageButtonEscrita);
        //imgBtnEscrita.setOnClickListener(this);
        edtTextMono = (EditText) view.findViewById(R.id.editTextMono);
        edtTextMono.setText(strEscritaMono);

        //setRetainInstance(true);
        Log.i("Script:","onCreate");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Script","Resume");
    }

    @Override
    public void onClick(View v) {
        if(v == imgBtn){
            Log.i("Script","onClick()");
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(intent,0);


        }else if(v == imgBtnEscrita){
            strEscritaMono = edtTextMono.getText().toString();
            strEscritaMono = GoogleVision.resposta;
            if(strEscritaMono != null) {
                edtTextMono.setText(strEscritaMono.replaceAll(" ", ""));
            }
        }
    }

    //Chamando Google Vision

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(data != null){

            Bundle bundle = data.getExtras();
            if (bundle != null){
                bitmapMono = (Bitmap) bundle.get("data");
                //bitmapMono = GoogleVision.scaleBitmapDown(bitmapMono,1200);
                //try {
                   // if(bitmapMono != null) {
                        //GoogleVision.callCloudVision(bitmapMono);
                    //    Log.i("Script:","Bitmap não é null: "+strEscritaMono);
                    //}else{
                     //   Log.i("Script:","Bitmap é null");
                    //}
                //} catch (IOException e) {
                 //   e.printStackTrace();
                //}
                imgEscrita.setImageBitmap(bitmapMono);

            }

        }


    }



    //Estruturando dimensões da imagem para melhor aproveitamento
    /*
    public Bitmap scaleBitmapDown(Bitmap bitmap, int maxDimension) {

        int originalWidth = bitmap.getWidth();
        int originalHeight = bitmap.getHeight();
        int resizedWidth = maxDimension;
        int resizedHeight = maxDimension;

        if (originalHeight > originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = (int) (resizedHeight * (float) originalWidth / (float) originalHeight);
        } else if (originalWidth > originalHeight) {
            resizedWidth = maxDimension;
            resizedHeight = (int) (resizedWidth * (float) originalHeight / (float) originalWidth);
        } else if (originalHeight == originalWidth) {
            resizedHeight = maxDimension;
            resizedWidth = maxDimension;
        }
        return Bitmap.createScaledBitmap(bitmap, resizedWidth, resizedHeight, false);
    }

    private void callCloudVision(final Bitmap bitmap) throws IOException {
        // Switch text to loading
        //mImageDetails.setText(R.string.loading_message);

        // Estruturando tarefas assincronas, é necessária a conexão com internet
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                try {
                    HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                    Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
                    builder.setVisionRequestInitializer(new
                            VisionRequestInitializer(CLOUD_VISION_API_KEY));
                    Vision vision = builder.build();

                    BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                            new BatchAnnotateImagesRequest();
                    batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
                        AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

                        // Adicinando imagem
                        Image base64EncodedImage = new Image();
                        // Convertendo imagem para JPEG
                        // Just in case it's a format that Android understands but Cloud Vision
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();

                        // Base64 codifica o JPEG
                        base64EncodedImage.encodeContent(imageBytes);
                        annotateImageRequest.setImage(base64EncodedImage);

                        // Adicionando dados que nós queremos, como detecção de caracteres
                        annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                            Feature textDetection = new Feature();
                            textDetection.setType("TEXT_DETECTION");
                            textDetection.setMaxResults(1);
                            add(textDetection);
                        }});

                        // Adicionando requisições
                        add(annotateImageRequest);
                    }});

                    Vision.Images.Annotate annotateRequest =
                            vision.images().annotate(batchAnnotateImagesRequest);
                    // Due to a bug: requests to Vision API containing large images fail when GZipped.
                    annotateRequest.setDisableGZipContent(true);
                    Log.d("Script :", "created Cloud Vision request object, sending request");

                    BatchAnnotateImagesResponse response = annotateRequest.execute();
                    return convertResponseToString(response);

                } catch (GoogleJsonResponseException e) {
                    Log.d("Script :", "failed to make API request because " + e.getContent());
                } catch (IOException e) {
                    Log.d("Script :", "failed to make API request because of other IOException " +
                            e.getMessage());
                }
                return "Cloud Vision API request failed. Check logs for details.";
            }
            protected void onPostExecute(String result) {
                Log.i("Resultado: ",result);
                //mImageDetails.setText(result);
            }
        }.execute();
    }

    private String convertResponseToString(BatchAnnotateImagesResponse response) {
        String message = "Nós achamos estes dados:\n\n";

        List<EntityAnnotation> texts = response.getResponses().get(0).getTextAnnotations();
        if (texts != null) {
            for (EntityAnnotation text : texts) {
                message += String.format("%.3f: %s", text.getScore(), text.getDescription());
                message += "\n";
            }

        } else {
            message += "nothing";
        }

        return message;
    }
    /*
    public void uploadImage(Uri uri) {
        if (uri != null) {
            try {
                // scale the image to save on bandwidth
                Bitmap bitmap =
                        scaleBitmapDown(
                                MediaStore.Images.Media.getBitmap(getContentResolver(), uri),
                                1200);

                callCloudVision(bitmap);
                mMainImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                Log.d(TAG, "Image picking failed because " + e.getMessage());
                Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
            }
        } else {
            Log.d(TAG, "Image picker gave us a null image.");
            Toast.makeText(this, R.string.image_picker_error, Toast.LENGTH_LONG).show();
        }
    }

    private void callCloudVision(final Bitmap bitmap) throws IOException {
        // Switch text to loading
        mImageDetails.setText(R.string.loading_message);

        // Do the real work in an async task, because we need to use the network anyway
        new AsyncTask<Object, Void, String>() {
            @Override
            protected String doInBackground(Object... params) {
                try {
                    HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
                    JsonFactory jsonFactory = GsonFactory.getDefaultInstance();

                    Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
                    builder.setVisionRequestInitializer(new
                            VisionRequestInitializer(CLOUD_VISION_API_KEY));
                    Vision vision = builder.build();

                    BatchAnnotateImagesRequest batchAnnotateImagesRequest =
                            new BatchAnnotateImagesRequest();
                    batchAnnotateImagesRequest.setRequests(new ArrayList<AnnotateImageRequest>() {{
                        AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();

                        // Add the image
                        Image base64EncodedImage = new Image();
                        // Convert the bitmap to a JPEG
                        // Just in case it's a format that Android understands but Cloud Vision
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
                        byte[] imageBytes = byteArrayOutputStream.toByteArray();

                        // Base64 encode the JPEG
                        base64EncodedImage.encodeContent(imageBytes);
                        annotateImageRequest.setImage(base64EncodedImage);

                        // add the features we want
                        annotateImageRequest.setFeatures(new ArrayList<Feature>() {{
                            Feature labelDetection = new Feature();
                            labelDetection.setType("LABEL_DETECTION");
                            labelDetection.setMaxResults(10);
                            add(labelDetection);
                        }});

                        // Add the list of one thing to the request
                        add(annotateImageRequest);
                    }});

                    Vision.Images.Annotate annotateRequest =
                            vision.images().annotate(batchAnnotateImagesRequest);
                    // Due to a bug: requests to Vision API containing large images fail when GZipped.
                    annotateRequest.setDisableGZipContent(true);
                    Log.d(TAG, "created Cloud Vision request object, sending request");

                    BatchAnnotateImagesResponse response = annotateRequest.execute();
                    return convertResponseToString(response);

                } catch (GoogleJsonResponseException e) {
                    Log.d(TAG, "failed to make API request because " + e.getContent());
                } catch (IOException e) {
                    Log.d(TAG, "failed to make API request because of other IOException " +
                            e.getMessage());
                }
                return "Cloud Vision API request failed. Check logs for details.";
            }

            protected void onPostExecute(String result) {
                mImageDetails.setText(result);
            }
        }.execute();
    }

    private String convertResponseToString(BatchAnnotateImagesResponse response) {
        String message = "I found these things:\n\n";

        List<EntityAnnotation> labels = response.getResponses().get(0).getLabelAnnotations();
        if (labels != null) {
            for (EntityAnnotation label : labels) {
                message += String.format("%.3f: %s", label.getScore(), label.getDescription());
                message += "\n";
            }
        } else {
            message += "nothing";
        }

        return message;
    }

    */

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt("contador",contador);
        Log.i("Script","onSaveInstanceState");

    }

    /*

    @Override
    public void onDestroy() {
        //((CadastroSondagemActivity)getActivity()).salvarDadosFragmentMono();
        //bitmapMono = null;
        GoogleVision.resposta = null;
        Log.i("Script","bitmap recebe null");
        super.onDestroy();
        Log.i("Script","onDestroy");
    }

    */

}
