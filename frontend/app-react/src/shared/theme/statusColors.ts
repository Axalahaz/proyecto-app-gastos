export const statusColors = {
    programado: {
        bg: "#fcf7ec",
        border: "#ffc20c",
        shadow: "#eab30859",
        text: "#a16207",
        badgeBg: "#fefaf2",
    },
    porVencer: {
        bg: "#f4eefd",
        border: "#ad8bfd",
        shadow: "#8b5cf659",
        text: "#6d28d9",
        badgeBg: "#f9f6fe",
    },
    pagado: {
        bg: "#ecf8e3",
        border: "#22c55e",
        shadow: "#22c55e59",
        text: "#15803d",
        badgeBg: "#f0f9e9",
    },
    vencido: {
        bg: "#fdf1ee",
        border: "#fd7373",
        shadow: "#ef444459",
        text: "#b91c1c",
        badgeBg: "#fff3f1",
    },
} as const satisfies Record<string,  {
    bg: string;
    border: string;
    shadow: string;
    text: string;
    badgeBg: string;
}>;