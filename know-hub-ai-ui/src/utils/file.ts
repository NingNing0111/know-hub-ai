export const downloadFile = (url: string) => {
  // Open the URL in a new window to trigger the download
  window.open(url, '_blank');
};

export const fileToBlob = (file: File): Promise<Blob> => {
  return new Promise((resolve) => {
    const blob = new Blob([file], { type: file.type });
    resolve(blob);
  });
};
