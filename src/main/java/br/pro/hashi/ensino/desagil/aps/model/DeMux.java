package br.pro.hashi.ensino.desagil.aps.model;

public class DeMux extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandBottomLeft;
    private final NandGate nandBottomRight;
    private final NandGate nandTopLeft;
    private final NandGate nandTopRight;

    public DeMux() {

        super("DeMux", 2, 2);
        nandLeft = new NandGate();
        nandBottomLeft = new NandGate();
        nandBottomRight = new NandGate();
        nandTopLeft = new NandGate();
        nandTopRight = new NandGate();

        nandTopLeft.connect(1, nandLeft);
        nandTopRight.connect(0, nandTopLeft);
        nandTopRight.connect(1, nandTopLeft);
        nandBottomRight.connect(0, nandBottomLeft);
        nandBottomRight.connect(1, nandBottomLeft);


    }

    @Override
    public boolean read(int outputPin) {

        if (outputPin < 0 || outputPin > 1) {
            throw new IndexOutOfBoundsException(outputPin);
        } else if (outputPin == 0) {
            return nandTopRight.read();
        } else {
            return nandBottomRight.read();
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTopLeft.connect(0, emitter);
                nandBottomLeft.connect(1, emitter);
                break;
            case 1:
                nandLeft.connect(0, emitter);
                nandLeft.connect(1, emitter);
                nandBottomLeft.connect(0, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
