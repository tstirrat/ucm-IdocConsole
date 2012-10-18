package org.stirrat.ecm.test.idocconsole.service;

import static org.junit.Assert.assertEquals;
import intradoc.common.ServiceException;
import intradoc.data.DataBinder;
import intradoc.server.PageMerger;
import intradoc.server.Service;
import intradoc.shared.SharedObjects;

import org.junit.Before;
import org.junit.Test;
import org.stirrat.ecm.idocconsole.service.IdocConsoleServices;

public class TestIdocConsoleServices {

  @Before
  public void setUp() {
    SharedObjects.init();
  }

  @Test
  public void testEvaluateIdocScriptMaintainsDataBinderIntegrity() throws ServiceException {
    DataBinder binder = new DataBinder();
    PageMerger pm = new PageMerger(binder, new Service());

    binder.putLocal("test", "A");

    IdocConsoleServices testServices = new IdocConsoleServices();

    testServices.evaluateIdocScript("", binder, pm);

    assertEquals("A", binder.getLocal("test"));
  }

  @Test
  public void testEvaluateIdocScriptCorrectlyEvaluatesBasicIdoc() throws ServiceException {
    DataBinder binder = new DataBinder();
    PageMerger pm = new PageMerger(binder, new Service());

    IdocConsoleServices testServices = new IdocConsoleServices();

    testServices.evaluateIdocScript("<$ tempVar = \"BLAH\" $><$ tempVar $>", binder, pm);

    assertEquals("BLAH", binder.getLocal("output"));
  }

  @Test(expected = ServiceException.class)
  public void testInvalidIdocScriptThrowsServiceException() throws ServiceException {
    DataBinder binder = new DataBinder();
    PageMerger pm = new PageMerger(binder, new Service());

    IdocConsoleServices testServices = new IdocConsoleServices();

    testServices.evaluateIdocScript("<$ a", binder, pm);
  }
}
