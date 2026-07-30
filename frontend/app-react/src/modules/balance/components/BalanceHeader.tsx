import { AppHeader } from "@/shared/components/AppHeader";

import type { ThemeProps } from "@/shared/theme/themes";

import {IconBalance } from '@/shared/icons/IconBalance';

export const BalanceHeader = ({theme}: ThemeProps) => {
    return (
        <AppHeader
            title="By JBYCode {}"
            icon={IconBalance}
            classIcon="w-6 h-6"
            theme={theme}
        />
    );
};