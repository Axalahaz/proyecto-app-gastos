interface HeaderWaveProps {
    color: string;
}

export const HeaderWave = ({ color }: HeaderWaveProps) => {
    const d: string = `
        M0,24
        L0,12

        C20,12 30,0 50,0
        C70,0 80,12 100,12
        C110,14,130,6,150,5
        C170,5,180,15,200,15
        C200,15,210,16,220,16
        L230,24
    `;

    return (
        <svg
            width="100%"
            viewBox="55 0 330 12"
            preserveAspectRatio="none"
        >
            <path
                d={d}
                fill={color}
            />
            <g transform="translate(440 0) scale(-1 1)">
                <path 
                    d={d}
                    fill={color}
                />
            </g>
        </svg>
    );
};