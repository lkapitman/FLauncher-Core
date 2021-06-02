package com.github.lkapitman.filemanager;


/**
 * The enum Operating system.
 */
public enum OperatingSystem {

    /**
     * The Linux.
     */
    LINUX("linux", new String[]{"linux", "unix"}),
    /**
     * The Windows.
     */
    WINDOWS("windows", new String[]{"win"}),
    /**
     * The Macos.
     */
    MACOS("osx", new String[]{"mac"}),
    /**
     * The Unknown.
     */
    UNKNOWN("unknown", new String[0]);

    private final String name;
    private final String[] aliases;

    OperatingSystem(final String name, final String[] aliases) {
        this.name = name;
        this.aliases = ((aliases == null) ? new String[0] : aliases);
    }

    /**
     * Gets currently platform.
     *
     * @return the currently platform
     */
    public static OperatingSystem getCurrentlyPlatform() {
        final String osName = System.getProperty("os.name").toLowerCase();
        for (final OperatingSystem os : values()) {
            for (final String alias : os.aliases) {
                if (osName.contains(alias))
                    return os;
            }
        }
        return OperatingSystem.UNKNOWN;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get aliases string [ ].
     *
     * @return the string [ ]
     */
    public String[] getAliases() {
        return aliases;
    }
}
