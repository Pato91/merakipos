/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meraki101;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javax.usb.*;
import javax.usb.event.*;
import org.usb4java.Context;
import org.usb4java.Device;
import org.usb4java.DeviceDescriptor;
import org.usb4java.HotplugCallback;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

/**
 *
 * @author meraki
 */
public class BarcodeClass {

    final short vendorId = (short) Integer.parseInt("05FE", 16);
    final short productId = (short) Integer.parseInt("1010", 16);
    private TextField x = new TextField();
    
    public BarcodeClass(TextField tx) {
        this.x = tx;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }

    ;

    public UsbDevice getUsbRootHoob() {

        try {
            final UsbServices services = UsbHostManager.getUsbServices();
            return services.getRootUsbHub();
        } catch (SecurityException | UsbException e) {
        }

        return null;
    }

    public UsbDevice findDevice() throws UsbException, UnsupportedEncodingException {

        return findDevice((UsbHub) getUsbRootHoob());
    }

    public UsbDevice findDevice(UsbHub hub) throws UsbException, UnsupportedEncodingException {

        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == this.vendorId && desc.idProduct() == this.productId) {

                this.readMessageAsynch((UsbInterface) device.getActiveUsbConfiguration().getUsbInterfaces().get(0));

                return device;
            }
            if (device.isUsbHub()) {
                device = findDevice((UsbHub) device);
                if (device != null) {
                    return device;
                }
            }

        }
        return null;
    }

    private void readMessageAsynch(UsbInterface iface) {

        final UsbPipe pipe;
        final UsbPipe pipex;

        try {
            try {
                iface.claim();
                UsbEndpoint endpoint = (UsbEndpoint) iface.getUsbEndpoint((byte) 0x81); // there can be more 1,2,3..
                pipe = endpoint.getUsbPipe();

                pipe.open();
                pipe.addUsbPipeListener(new UsbPipeListener() {
                    HIDCodeMapper HIDCodeMapper = new HIDCodeMapper();

                    @Override
                    public void errorEventOccurred(UsbPipeErrorEvent event) {
                        UsbException error = event.getUsbException();
                        System.out.println(error);
                    }

                    @Override
                    public void dataEventOccurred(UsbPipeDataEvent event) {
                        try {
                            byte[] datas = event.getData();

                            byte[] f = new byte[datas.length];
                            int ct = 0;
                            for (int x = 0; x < datas.length; x++) {
                                f[ct++] = (byte) (x);
                            }

                            ArrayList<Integer> arrL = new ArrayList<>();
                            for (byte data : datas) {
                                if (data != 0) {
                                    arrL.add((int) (data));
                                }
                            }
                            char[] c = new char[arrL.size()];
                            int count = 0;
                            for (int b_val : arrL) {
                                c[count++] = (char) b_val;
                            }
                            String barcodeChar = HIDCodeMapper.hidString(c);
                            if (barcodeChar != null) {
                                x.setText(x.getText() + barcodeChar);
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(BarcodeClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            if (!iface.isClaimed()) {
                                iface.claim((UsbInterface usbInterface) -> true);
                                pipe.open();
                            }
                            byte[] data = new byte[8];
                            pipe.syncSubmit(data);
                        } catch (UsbNotActiveException | UsbNotOpenException | IllegalArgumentException | UsbDisconnectedException ex) {
                            try {
                                if (iface.isClaimed() && pipe.isOpen()) {
                                    pipe.close();
                                    iface.release();
                                }

                            } catch (UsbException ex1) {
                                ex1.printStackTrace();
                                System.out.println("Releasing resources....");
                                timer.cancel();
                            }
                        } catch (UsbException ex) {
                            System.out.println("Releasing resources");
                        }
                    }
                };

                timer.schedule(task, 10, 5);

            } finally {
                iface.claim((UsbInterface usbInterface) -> true);
                UsbEndpoint endpoint = (UsbEndpoint) iface.getUsbEndpoint((byte) 0x81); // there can be more 1,2,3..
                pipex = endpoint.getUsbPipe();

                pipex.open();
                pipex.addUsbPipeListener(new UsbPipeListener() {
                    HIDCodeMapper HIDCodeMapper = new HIDCodeMapper();

                    @Override
                    public void errorEventOccurred(UsbPipeErrorEvent event) {
                        UsbException error = event.getUsbException();
                        System.out.println(error);
                    }

                    @Override
                    public void dataEventOccurred(UsbPipeDataEvent event) {
                        try {
                            byte[] datas = event.getData();

                            byte[] f = new byte[datas.length];
                            int ct = 0;
                            for (int x = 0; x < datas.length; x++) {
                                f[ct++] = (byte) (x);
                            }

                            ArrayList<Integer> arrL = new ArrayList<>();
                            for (byte data : datas) {
                                if (data != 0) {
                                    arrL.add((int) (data));
                                }
                            }
                            char[] c = new char[arrL.size()];
                            int count = 0;
                            for (int b_val : arrL) {
                                c[count++] = (char) b_val;
                            }
                            String barcodeChar = HIDCodeMapper.hidString(c);
                            if (barcodeChar != null) {
                                x.setText(x.getText() + barcodeChar);
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(BarcodeClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });

                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            if (!iface.isClaimed()) {
                                iface.claim((UsbInterface usbInterface) -> true);
                                pipex.open();
                            }
                            byte[] data = new byte[8];
                            pipex.syncSubmit(data);
                        } catch (UsbNotActiveException | UsbNotOpenException | IllegalArgumentException | UsbDisconnectedException ex) {
                            try {
                                if (iface.isClaimed() && pipex.isOpen()) {
                                    pipex.close();
                                    iface.release();
                                }

                            } catch (UsbException ex1) {
                                timer.cancel();
                            }
                        } catch (UsbException ex) {
                            timer.cancel();
                        }
                    }
                };

                timer.schedule(task, 10, 5);
            }

        } catch (UsbException
                | UsbNotActiveException | UsbNotClaimedException ex) {
        } finally {
            try {
                iface.getUsbEndpoint((byte) 0x81).getUsbPipe().close();
                iface.release();
            } catch (UsbClaimException e) {
            } catch (UsbNotActiveException
                    | UsbException e) {
            }
        }

    }

    private class HIDCodeMapper {

        private HashMap codesMap;

        public String hidString(char[] codesArray) {
            if (codesArray.length > 0) {
                String code = "";
                HashMap codesHashMap = this.returnCodes();
                for (char code_val : codesArray) {
                    if (!"".equals(code_val)) {
                        String hexStr = Integer.toHexString((int) code_val).toUpperCase();
                        if (hexStr.equals("A") || hexStr.equals("B") || hexStr.equals("C") || hexStr.equals("D") || hexStr.equals("E") || hexStr.equals("F")) {
                            hexStr = "0" + hexStr;
                        }
                        if (codesHashMap.get(hexStr) != null && !"".equals(hexStr)) {
                            code += codesHashMap.get(hexStr);
                        }
                    }
                }
                return code;
            } else {
                return null;
            }
        }

        private HashMap returnCodes() {
            codesMap = new HashMap();
            codesMap.put("04", "A");
            codesMap.put("05", "B");
            codesMap.put("06", "C");
            codesMap.put("07", "D");
            codesMap.put("08", "E");
            codesMap.put("09", "F");
            codesMap.put("0A", "G");
            codesMap.put("0B", "H");
            codesMap.put("0C", "I");
            codesMap.put("0D", "J");
            codesMap.put("0E", "K");
            codesMap.put("0F", "L");
            codesMap.put("10", "M");
            codesMap.put("11", "N");
            codesMap.put("12", "O");
            codesMap.put("13", "P");
            codesMap.put("14", "Q");
            codesMap.put("15", "R");
            codesMap.put("16", "S");
            codesMap.put("17", "T");
            codesMap.put("18", "U");
            codesMap.put("19", "V");
            codesMap.put("1A", "W");
            codesMap.put("1B", "X");
            codesMap.put("1C", "Y");
            codesMap.put("1D", "Z");
            codesMap.put("1E", "1");
            codesMap.put("1F", "2");
            codesMap.put("20", "3");
            codesMap.put("21", "4");
            codesMap.put("22", "5");
            codesMap.put("23", "6");
            codesMap.put("24", "7");
            codesMap.put("25", "8");
            codesMap.put("26", "9");
            codesMap.put("27", "0");
            codesMap.put("2F", "[");
            codesMap.put("30", "]");
            codesMap.put("36", "<");
            codesMap.put("37", ">");
            codesMap.put("38", "/");

            return codesMap;
        }
    }

}
