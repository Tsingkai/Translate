package translate.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import translate.Activator;

public class TranslatePreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(TranslatePreferenceConstants.SOURCE, "auto");
		store.setDefault(TranslatePreferenceConstants.TARGET, "zh");
		store.setDefault(TranslatePreferenceConstants.APP_ID, "");
		store.setDefault(TranslatePreferenceConstants.SECRET_KEY, "");
	}

}
