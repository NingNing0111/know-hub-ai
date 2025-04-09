import { useState } from 'react';

const useMenuCollapsed = () => {
  const [menuCollapsed, setMenuCollapsed] = useState<boolean>(true);
  return {
    menuCollapsed,
    setMenuCollapsed,
  };
};

export default useMenuCollapsed;
