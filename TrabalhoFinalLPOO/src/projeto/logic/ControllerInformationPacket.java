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

    /**
     * Contrutor da classe
     * @param x coordenada x da posicao onde desenhar o botao no android
     * @param y coordenada y da posicao onde desenhar o botao no android
     * @param w largura do controlador
     * @param h altura do controlador
     * @param tipo de controlador
     * @param pos numero do controlador
     */
    public ControllerInformationPacket(float x, float y, float w, float h, Type tipo, byte pos){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.tipo = tipo;
        this.pos = pos;
    }

    /**
     * Contrutor da classe
     * @param x coordenada x da posicao onde desenhar o botao no android
     * @param y coordenada y da posicao onde desenhar o botao no android
     * @param w largura do controlador
     * @param h altura do controlador
     * @param botaoNome nome do botao
     * @param pos numero do controlador
     */
    public ControllerInformationPacket(float x, float y, float w, float h,String botaoNome, byte pos){
        tipo = Type.BUTTON;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.botaoNome = botaoNome;
        this.pos = pos;
    }

    /**
     * Funcao get para o tipo de controlador
     * @return tipo de controlador
     */
    public Type getTipo(){
        return tipo;
    }

    /**
     * Funcao get para a coordenada x
     * @return coordenada x
     */
    public float getX(){
        return x;
    }

    /**
     * Funcao get para a coordenada y
     * @return coordenada y
     */
    public float getY(){
        return y;
    }

    /**
     * Funcao get para a largura
     * @return largura
     */
    public float getW(){
        return w;
    }

    /**
     * Funcao get para a altura
     * @return altura
     */
    public float getH(){
        return h;
    }

    /**
     * Funcao get para o nome do botao
     * @return nome do botao
     */
    public String getButaoNome(){
        return botaoNome;
    }

    /**
     * Funcao get para a posicao do controlador
     * @return posicao do controlador
     */
    public byte getPositionToSend(){
        return pos;
    }

}
