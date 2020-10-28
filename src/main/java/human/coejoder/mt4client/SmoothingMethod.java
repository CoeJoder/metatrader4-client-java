package human.coejoder.mt4client;

/**
 * Many technical indicators are based on various methods of the price series smoothing. Some standard technical
 * indicators (iAlligator(), iEnvelopes(), iEnvelopesOnArray(), iForce(), iGator(), iMA(), iMAOnArray(), iStdDev(),
 * iStdDevOnArray() and iStochastic() indicators) require specification of the smoothing type as an input parameter. For
 * specifying the desired type of smoothing, identifiers listed in the ENUM_MA_METHOD enumeration are used.
 */
public enum SmoothingMethod {
    SMA(0),
    EMA(1),
    SMMA(2),
    LWMA(3);

    public final int id;

    SmoothingMethod(int id) {
        this.id = id;
    }
}
