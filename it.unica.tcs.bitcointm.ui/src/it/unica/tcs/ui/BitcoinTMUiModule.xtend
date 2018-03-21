/*
 * Copyright 2017 Nicola Atzei
 */

/*
 * generated by Xtext 2.11.0
 */
package it.unica.tcs.ui

import it.unica.tcs.ui.hover.BitcoinTMEObjectHoverProvider
import org.eclipse.equinox.security.storage.ISecurePreferences
import org.eclipse.equinox.security.storage.SecurePreferencesFactory
import org.eclipse.ui.plugin.AbstractUIPlugin
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
class BitcoinTMUiModule extends AbstractBitcoinTMUiModule {

    new(AbstractUIPlugin plugin) {
        super(plugin)
    }

    def Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider() {
        return  BitcoinTMEObjectHoverProvider;
    }

    def ISecurePreferences bindISecurePreferences() {
        return SecurePreferencesFactory.getDefault()
    }
}
