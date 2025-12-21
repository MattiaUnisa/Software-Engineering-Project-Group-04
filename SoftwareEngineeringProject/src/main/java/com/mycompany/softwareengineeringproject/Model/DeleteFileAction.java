package com.mycompany.softwareengineeringproject.Model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DeleteFileAction implements Action {

    private final File file;

    public DeleteFileAction(File file) {
        this.file = file;
    }

    @Override
    public void execute(ActionContext context) {

        // Check if the file exists
        if (file == null || !file.exists()) {
            context.appendLog("ERROR: File does not exist: " + file);

            if (context.getUiEventListener() != null) {
                context.getUiEventListener()
                        .onShowError("Error", "Cannot delete the file", "File does not exist");
            }
            return;
        }

        // Check that it is not a directory (optional but safer)
        if (file.isDirectory()) {
            context.appendLog("ERROR: Selected path is a directory, not a file: " + file.getAbsolutePath());

            if (context.getUiEventListener() != null) {
                context.getUiEventListener()
                        .onShowError("Error", "Cannot delete the file", "Selected path is a directory");
            }
            return;
        }

        try {
            // Delete the file
            Files.delete(file.toPath());

            context.appendLog("SUCCESS: File deleted: " + file.getAbsolutePath());

            if (context.getUiEventListener() != null) {
                context.getUiEventListener()
                        .onShowNotification("Success", "File deleted successfully");
            }

        } catch (IOException e) {
            context.appendLog("ERROR: File deletion failed: " + e.getMessage());

            if (context.getUiEventListener() != null) {
                context.getUiEventListener()
                        .onShowError("Error", "Cannot delete the file", e.getMessage());
            }
        }
    }

    @Override
    public void stop() {
        // No stop logic needed for this action
    }

    @Override
    public String formatString() {
        // Used to serialize the action
        return "DeleteFile:" + file;
    }

    public static Action parseString(String action) {

        if (!action.startsWith("DeleteFile:")) {
            throw new IllegalArgumentException("Invalid DeleteFileAction format.");
        }

        String data = action.substring("DeleteFile:".length());

        File file = new File(data);
        return ActionFactory.createDeleteFile(file);
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "Delete File: " + (file != null ? file.getAbsolutePath() : "null");
    }
}
