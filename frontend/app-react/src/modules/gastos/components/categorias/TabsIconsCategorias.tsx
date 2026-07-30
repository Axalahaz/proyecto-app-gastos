import { useState } from 'react';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import PhoneIcon from '@mui/icons-material/Phone';
import FavoriteIcon from '@mui/icons-material/Favorite';
import PersonPinIcon from '@mui/icons-material/PersonPin';

export const TabsIconsCategorias = () => {
    const [value, setValue] = useState(0);

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    //{categoriasCantidad.map((categoria) => (
    //                                <button key={categoria.id}
    //                                className="shrink-0 mx-2 first:ms-0"
    //                                onClick={() => handleCategoriaClick(categoria)}
    //                                >
    //                                    <CategoriasCard categoria={categoria}/>
    //                                </button>
    //                            ))}
    return (
        
        <Tabs value={value} onChange={handleChange} aria-label="icon categoria tabs">
            
        <Tab icon={<PhoneIcon />} label="RECENTS" />
        <Tab icon={<FavoriteIcon />} label="FAVORITES" />
        <Tab icon={<PersonPinIcon />} label="NEARBY" />
        </Tabs>
    );
}