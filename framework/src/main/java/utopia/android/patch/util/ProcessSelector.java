package utopia.android.patch.util;

import utopia.android.patch.lang.PatchLoader;

public abstract class ProcessSelector implements PatchLoader {
    protected final String mPackageName; //target app

    protected ProcessSelector(String packageName) {
        mPackageName = packageName;
    }

    @Override
    public final void loadPatch() {
        if (!HiddenAPI.currentPackageName().equals(mPackageName)) {
            return;
        }
        try {
            loadAll();
        } finally {
            if (HiddenAPI.currentProcessName().equals(mPackageName)) {
                loadOne();
            }
        }
    }

    /**
     * Call in all processes.
     */
    protected abstract void loadAll();

    /**
     * Call in main process only.
     */
    protected abstract void loadOne();
}
