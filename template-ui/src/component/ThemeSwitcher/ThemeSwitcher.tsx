import { useAntdConfigSetter } from '@umijs/max';
import { theme } from 'antd';
import React, { useEffect, useState } from 'react';
import { CiLight } from 'react-icons/ci';
import { MdOutlineDarkMode } from 'react-icons/md';

const { darkAlgorithm, defaultAlgorithm } = theme;

const ThemeSwitcher: React.FC = () => {
  const [themeMode, setThemeMode] = useState<'light' | 'dark'>(
    (localStorage.getItem('vite-ui-theme') as 'light' | 'dark') || 'light',
  );
  const antdConfigSetter = useAntdConfigSetter();

  useEffect(() => {
    const algo = themeMode === 'dark' ? darkAlgorithm : defaultAlgorithm;
    antdConfigSetter({
      theme: {
        algorithm: [algo],
      },
    });
    document.documentElement.setAttribute('data-theme', themeMode);
    localStorage.setItem('vite-ui-theme', themeMode);
  }, [themeMode]);

  return themeMode === 'light' ? (
    <MdOutlineDarkMode
      size={35}
      style={{ cursor: 'pointer' }}
      onClick={() => setThemeMode('dark')}
      title="切换为暗黑模式"
    />
  ) : (
    <CiLight
      size={35}
      style={{ cursor: 'pointer' }}
      onClick={() => setThemeMode('light')}
      title="切换为明亮模式"
    />
  );
};

export default ThemeSwitcher;
