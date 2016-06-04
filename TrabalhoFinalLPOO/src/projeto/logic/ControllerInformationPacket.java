package projeto.logic;

import java.io.Serializable;

/**
 * @author João e David
 * @version 1.0
 * @created 31-mai-2016 15:30:32.
 */
public class ControllerInformationPacket implements Serializable {

    static final long serialVersionUID = 40L;

    public enum Type{
        BUTTON,
        JOYSTICK
    }

    private Type tipo;
    private float x,y,w,h;
    private String botaoNome;
    private byte pos;


    public ControllerInformationPacket(float x, float y, float w, float h, Type tipo, byte pos){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.tipo = tipo;
        this.pos = pos;
    }

    public ControllerInformationPacket(float x, float y, float w, float h,String botaoNome, byte pos){
        tipo = Type.BUTTON;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.botaoNome = botaoNome;
        this.pos = pos;
    }

    public Type getTipo(){
        return tipo;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public float getW(){
        return w;
    }

    public float getH(){
        return h;
    }

    public String getButaoNome(){
        return botaoNome;
    }

    public byte getPositionToSend(){
        return pos;
    }

}
