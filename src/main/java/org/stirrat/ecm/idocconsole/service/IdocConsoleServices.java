package org.stirrat.ecm.idocconsole.service;

import intradoc.common.ServiceException;
import intradoc.data.DataBinder;
import intradoc.server.PageMerger;

import org.ucmtwine.annotation.Binder;
import org.ucmtwine.annotation.ServiceMethod;

public class IdocConsoleServices {

  /**
   * Evaluate idoc script supplied as a string.
   * 
   * @param eval
   *          The idoc string to eval.
   * @param outputBinder
   *          The output DataBinder
   * @param service
   *          The service context
   * @throws ServiceException
   *           If something goes wrong evaluating the script.
   */
  @ServiceMethod(name = "TS_EVALUATE_IDOC", template = "TS_IDOC_CONSOLE")
  public void evaluateIdocScript(@Binder(name = "eval") String eval, DataBinder outputBinder, PageMerger pm)
      throws ServiceException {

    String output = "";

    try {
      output = pm.evaluateScriptEx(eval, false);

    } catch (Exception e) {
      throw new ServiceException(e.getMessage());
    }

    outputBinder.putLocal("output", output);
  }
}
