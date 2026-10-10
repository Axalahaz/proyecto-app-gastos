import * as React from "react";
import { NumberField as BaseNumberField } from "@base-ui/react/number-field";

import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";
import OutlinedInput from "@mui/material/OutlinedInput";

import AddIcon from "@mui/icons-material/Add";
import RemoveIcon from "@mui/icons-material/Remove";
import OpenInFullIcon from "@mui/icons-material/OpenInFull";

import type { ThemeProps } from "@/shared/theme/themes";

export default function NumberSpinner({
    id: idProp,
    label,
    error,
    size = "medium",
    theme,
    ...other
}: BaseNumberField.Root.Props &
    ThemeProps & {
        label?: React.ReactNode;
        size?: "small" | "medium";
        error?: boolean;
    }) {
    let id = React.useId();

    if (idProp) {
        id = idProp;
    }

    return (
        <BaseNumberField.Root
            {...other}
            render={(props, state) => (
                <FormControl
                    size={size}
                    ref={props.ref}
                    disabled={state.disabled}
                    required={state.required}
                    error={error}
                    variant="outlined"
                    sx={{
                        "& .MuiButton-root": {
                            minWidth: 0,
                            borderColor: "black",
                            backgroundColor: "transparent",

                            "&:not(.Mui-disabled)": {
                                color: theme.colors[900],
                            },

                            "&:hover:not(.Mui-disabled)": {
                                backgroundColor: theme.colors[900],
                                color: theme.colors[100],
                                borderColor: theme.colors[500],
                            },
                        },
                    }}
                >
                    {props.children}
                </FormControl>
            )}
        >
            <BaseNumberField.ScrubArea
                render={
                    <Box
                        component="span"
                        sx={{
                            userSelect: "none",
                            width: "max-content",
                        }}
                    />
                }
            >
                <FormLabel
                    htmlFor={id}
                    sx={{
                        fontSize: "0.9rem",

                        color: "black",

                        "&.Mui-focused": {
                            color: "black",
                        },
                    }}
                >
                    {label}

                    <BaseNumberField.ScrubAreaCursor>
                        <OpenInFullIcon
                            fontSize="small"
                            sx={{
                                transform:
                                    "translateY(12.5%) rotate(45deg)",
                                color: theme.colors[700],
                            }}
                        />
                    </BaseNumberField.ScrubAreaCursor>
                </FormLabel>
            </BaseNumberField.ScrubArea>

            <Box sx={{ display: "flex" }}>
                <BaseNumberField.Decrement
                    render={
                        <Button
                            variant="outlined"
                            aria-label="Decrease"
                            size={size}
                            sx={{
                                borderTopRightRadius: 0,
                                borderBottomRightRadius: 0,
                                borderRight: "0px",

                                "&.Mui-disabled": {
                                    borderRight: "0px",
                                },
                            }}
                        />
                    }
                >
                    <RemoveIcon fontSize={size} />
                </BaseNumberField.Decrement>

                <BaseNumberField.Input
                    id={id}
                    render={(props, state) => (
                        <OutlinedInput
                            inputRef={props.ref}
                            value={state.inputValue}
                            onBlur={props.onBlur}
                            onChange={props.onChange}
                            onKeyUp={props.onKeyUp}
                            onKeyDown={props.onKeyDown}
                            onFocus={props.onFocus}
                            slotProps={{
                                input: {
                                    ...props,
                                    size:
                                        Math.max(
                                            (
                                                other.min?.toString() || ""
                                            ).length,
                                            state.inputValue.length || 1,
                                        ) + 1,
                                    sx: {
                                        textAlign: "center",
                                        fontSize: "1.2rem",
                                        fontFamily:"var(--font-beiruti)",
                                        fontWeight: 500,
                                    },
                                },
                            }}
                            sx={{
                                pr: 0,
                                borderRadius: 0,
                                flex: 1,

                                "& .MuiOutlinedInput-notchedOutline": {
                                    borderColor: theme.colors[900],
                                },

                                "&:hover .MuiOutlinedInput-notchedOutline": {
                                    borderColor: theme.colors[700],
                                },

                                "&.Mui-focused .MuiOutlinedInput-notchedOutline":
                                    {
                                        borderColor: theme.colors[700],
                                        borderWidth: 2,
                                    },
                            }}
                        />
                    )}
                />

                <BaseNumberField.Increment
                    render={
                        <Button
                            variant="outlined"
                            aria-label="Increase"
                            size={size}
                            sx={{
                                borderTopLeftRadius: 0,
                                borderBottomLeftRadius: 0,
                                borderLeft: "0px",

                                "&.Mui-disabled": {
                                    borderLeft: "0px",
                                },
                            }}
                        />
                    }
                >
                    <AddIcon fontSize={size} />
                </BaseNumberField.Increment>
            </Box>
        </BaseNumberField.Root>
    );
}

