export const IconOk = (
    props: React.SVGProps<SVGSVGElement>
) => {
    return (
        <svg
        viewBox="0 0 120 80"
        fill="currentColor"
        xmlns="http://www.w3.org/2000/svg"
        {...props}
        >
            {/* O (semi círculo estilo dinámico) */}
            <path
                d="M38 20
                C20 20 20 60 38 60"
                stroke="currentColor"
                strokeWidth="10"
                strokeLinecap="round"
            />

            <path
                d="M38 20
                C56 20 56 60 38 60"
                fill="white"
                stroke="currentColor"
                strokeWidth="10"
                strokeLinecap="round"
                strokeLinejoin="round"
            />

            {/* K */}
            <path
                d="M70 18V62"
                stroke="currentColor"
                strokeWidth="10"
                strokeLinecap="round"
            />

            <path
                d="M70 40L95 20"
                stroke="currentColor"
                strokeWidth="10"
                strokeLinecap="round"
            />

            <path
                d="M70 40L95 62"
                stroke="currentColor"
                strokeWidth="10"
                strokeLinecap="round"
            />
        </svg>

    );
};