package org.jvnet.hudson.plugins.sendtomq.util;

import hudson.model.ParameterValue;
import hudson.model.Result;
import hudson.model.ParametersAction;
import hudson.model.Run;
import hudson.model.StringParameterValue;

public final class BuildUtil {

    public static String getReleaseVersion(Run run) {
        String releaseVersion = "";
        ParametersAction parametersAction = run.getAction(ParametersAction.class);

        if (parametersAction != null) {
            for (ParameterValue parametro : parametersAction.getParameters()) {
                if (parametro instanceof StringParameterValue && "RELEASE_VERSION".equals(((StringParameterValue) parametro).getName())) {
                    releaseVersion = ((StringParameterValue) parametro).value;
                    break;
                }
            }
        }
        return releaseVersion;
    }

    public static boolean isRelease(Run run) {
        if (! getReleaseVersion(run).trim().equals("")) {
            return true;
        }
        return false;
    }
    
    public static boolean isReleaseComSucesso(Run run) {
        if (! getReleaseVersion(run).trim().equals("") && (run.getResult() == Result.SUCCESS || run.getResult() == Result.UNSTABLE)) {
            return true;
        }
        return false;
    }
}
