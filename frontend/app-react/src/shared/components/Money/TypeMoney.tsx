
type Props = {
  typeMoney: string;
  className?: string;
  style?: {};
}

// !! Despues tengo que agregar a contex la configuracion de tipo de moneda!!

export const TypeMoney = ({typeMoney, className, style}: Props) => {
    return (
        <p 
          className={className}
          style={{...style}}
        >
          {typeMoney}
        </p>
    );
}