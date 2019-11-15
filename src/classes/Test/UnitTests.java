package classes.Test;

import javax.swing.*;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import classes.Devices.*;
import classes.GUI.DeviceCanvas;
import classes.GUI.DeviceEditorPopupFactory;
import classes.GUI.SplitPanel;
import junit.framework.*;

public class UnitTests {
  public void test(String[] args) {
    TestDeviceFactory();
    TestDevicePopupFactory();
    TestSplitPanel();
  }

  @Test
  private void TestDeviceFactory() {
    DeviceFactory testFactory = new DeviceFactory();
    assertEquals(Router.class, testFactory.create("Router"));
    assertEquals(Vm.class, testFactory.create(("Vm")));
  }

  @Test
  private void TestDevicePopupFactory() {
    DeviceEditorPopupFactory testFactory = new DeviceEditorPopupFactory();
    assertEquals(Router.class, testFactory.create("Router"));
    assertEquals(Vm.class, testFactory.create(("Vm")));
  }

  @Test
  private void TestSplitPanel() {
    SplitPanel testPanel = new SplitPanel();
    ActionEvent e = new ActionEvent();
    assertEquals(null, testPanel.actionPerformed(e));
  }

}