import React from 'react';

interface Props {
  buttons: React.ReactNode[];
}

const PageExtraButtons = (props: Props) => {
  return (
    <div
      style={{
        gap: 10,
        display: 'flex',
        justifyContent: 'center',
      }}
    >
      {props.buttons.map((item) => {
        return item;
      })}
    </div>
  );
};

export default PageExtraButtons;
