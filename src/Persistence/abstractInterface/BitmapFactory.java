package Persistence.abstractInterface;

import Model.Bitmap;

public interface BitmapFactory<Parameter> {

    public Bitmap createBitmap(Parameter input);
    
}