package it.sevenbits.homeworktwo.core.validation.status.service;

/**
 * Service provides task status validation.
 */
public class TaskStatusValidator implements ITaskStatusValidator {
    /**
     * Method that presents validation of passed task status.
     *
     * @param status Task status to validate.
     * @return Boolean value that represents validation result.
     */
    @Override
    public boolean isValidStatus(final String status) {
        String statusInbox = "inbox";
        return status.equals(statusInbox) || status.equals("");
    }
}
